package com.nhanlovecode.doancuoiky.Views.Personal.Profile;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;
import com.nhanlovecode.doancuoiky.R;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ChangePassActivity extends AppCompatActivity {
    TextInputEditText passNow, passNew, passNew1;
    TextInputLayout layoutPassOld, layoutPassNew, layoutPassNew1;
    LinearLayout btnChangePass;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RepositoryAPI repositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        initUI();
        eventClick();

    }

    private void eventClick() {
        btnChangePass.setOnClickListener(view -> {
            changePass();
        });
    }

    private void changePass() {
        String passold = String.valueOf(passNow.getText());
        String passoldmd5 = getMd5(String.valueOf(passNow.getText()));
        String passnew = String.valueOf(passNew.getText());
        String passnew1 = String.valueOf(passNew1.getText());
//        String passold1 = passold
        if(passold.isEmpty() || passnew.isEmpty() || passnew1.isEmpty()){
            Toast.makeText(this, "Hãy nhập đầy đủ các trường", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!passnew.equals(passnew1)){
            Toast.makeText(this, "Mật khẩu và mật khẩu nhập lại không khớp", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!passoldmd5.equals(MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_password())){
            Toast.makeText(this, "Mật khẩu hiện tại không đúng!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(passold.equals(passnew)){
            Toast.makeText(this, "Mật khẩu mới giống với mật khẩu hiện tại", Toast.LENGTH_SHORT).show();
            return;
        }
        compositeDisposable.add(repositoryAPI.updateUserPass(passnew, Constant.CUSTOMER_ID).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(customerAPI -> {
                    MySharedPreferencesManager.putCustomer(Constant.PREF_KEY_CUSTOMER, customerAPI.getData().get(0));
                    Toast.makeText(this, "Đã đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                    finish();
                    }, throwable -> {
                    Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }

    private void initUI() {
        passNow = findViewById(R.id.passNow);
        passNew = findViewById(R.id.passNew);
        passNew1 = findViewById(R.id.passNew1);
        btnChangePass = findViewById(R.id.btnChangePass);
    }
    public static String getMd5(String input) {

        // Static getInstance method is called with hashing MD5
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        // digest() method is called to calculate message digest
        // of an input digest() return array of byte
        byte[] messageDigest = md.digest(input.getBytes());

        // Convert byte array into signum representation
        BigInteger no = new BigInteger(1, messageDigest);

        // Convert message digest into hex value
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }
}