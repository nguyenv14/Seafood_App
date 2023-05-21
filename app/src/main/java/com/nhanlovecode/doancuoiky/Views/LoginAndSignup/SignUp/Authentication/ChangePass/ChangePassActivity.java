package com.nhanlovecode.doancuoiky.Views.LoginAndSignup.SignUp.Authentication.ChangePass;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.LoginAndSignup.LoginAndSignupActivity;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ChangePassActivity extends AppCompatActivity {
    TextInputLayout layoutPass1, layoutPass2;

    TextInputEditText addPass1, addPass2;
    String email;
    LinearLayout btnChangePass;

    RepositoryAPI repositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass2);
        initUI();
        email = getIntent().getStringExtra("customer_email");
        addPass2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = charSequence.toString();
                String textPass1 = addPass1.getText().toString();
                if(text.equals(textPass1)){
                    layoutPass2.setError(null);
                }else{
                    layoutPass2.setError("Password không đúng với ở trên!");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        eventClick();
    }

    private void eventClick() {
        btnChangePass.setOnClickListener(view -> {
            compositeDisposable.add(repositoryAPI.changePass(email, addPass1.getText().toString()).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(customerAPI -> {
                        Toast.makeText(this, "Đã đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, LoginAndSignupActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }, throwable -> {
                        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }));
        });
    }

    private void initUI() {
        addPass1 = findViewById(R.id.addPass1);
        addPass2 = findViewById(R.id.addPass2);
        layoutPass1 = findViewById(R.id.layoutPass1);
        layoutPass2 = findViewById(R.id.layoutPass2);
        btnChangePass = findViewById(R.id.btnChangePass);
    }
}