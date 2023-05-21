package com.nhanlovecode.doancuoiky.Views.Personal.Profile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.Models.Customer;
import com.nhanlovecode.doancuoiky.R;

public class ProfileActivity extends AppCompatActivity implements ProfileMVPView{
    TextView customer_name, customer_phone, customer_email;
    LinearLayout btnChangePassword, btnChangeName, btnChangePhone, btnChangeEmail;
    TextView tvTypeName;
    ImageView btnBack;
    EditText edtUpdateDialog;
    ProfilePresenter profilePresenter;
    LinearLayout btnCancelDialog, btnUpdateDialog;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profilePresenter = new ProfilePresenter(this, this);
        dialog = new Dialog(this);
        initUI();
        setData();
        eventClick();

    }

    private void eventClick() {
        eventClickDialog();
    }


    private void initUI() {
        customer_name = findViewById(R.id.customer_name);
        customer_phone = findViewById(R.id.customer_phone);
        customer_email = findViewById(R.id.customer_email);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnChangeName = findViewById(R.id.btnChangeName);
        btnChangePhone = findViewById(R.id.btnChangePhone);
        btnChangeEmail = findViewById(R.id.btnChangeEmail);
        btnBack = findViewById(R.id.btnBack);
    }
    private void initUIDialog(int type, String edit) {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);

        Window window = dialog.getWindow();
        if (window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttribute = window.getAttributes();
        windowAttribute.gravity = Gravity.CENTER;
        window.setAttributes(windowAttribute);
        dialog.setCancelable(false);
        dialog.show();

        tvTypeName = dialog.findViewById(R.id.tvTypeName);
        edtUpdateDialog = dialog.findViewById(R.id.edtUpdate);
        btnCancelDialog = dialog.findViewById(R.id.btnCancelDialog);
        btnUpdateDialog = dialog.findViewById(R.id.btnUpdateDialog);


        btnCancelDialog.setOnClickListener(view -> {
            dialog.dismiss();

        });
        if(type == 1){
            edtUpdateDialog.setText(edit);
            tvTypeName.setText("Tên khách hàng");
            btnUpdateDialog.setOnClickListener(view -> {
                profilePresenter.updateName(edtUpdateDialog.getText().toString(), Constant.CUSTOMER_ID);
                dialog.dismiss();
            });
        }else if(type == 2){
            edtUpdateDialog.setText(edit);
            tvTypeName.setText("Số điện thoại");
            btnUpdateDialog.setOnClickListener(view -> {
                profilePresenter.updatePhone(Long.parseLong(edtUpdateDialog.getText().toString()), Constant.CUSTOMER_ID);
                dialog.dismiss();
            });
        } else if (type == 3) {
            edtUpdateDialog.setText(edit);
            tvTypeName.setText("Email khách hàng");
            btnUpdateDialog.setOnClickListener(view -> {
                profilePresenter.updateEmail(edtUpdateDialog.getText().toString(), Constant.CUSTOMER_ID);
                dialog.dismiss();
            });
        }
    }
    private void setData() {
        Customer customer = MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER);
        customer_name.setText(customer.getCustomer_name());
        customer_phone.setText("0" + customer.getCustomer_phone());
        customer_email.setText(customer.getCustomer_email());
        Toast.makeText(this, "anhooo", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void eventClickDialog() {
        btnChangeName.setOnClickListener(view -> {
            initUIDialog(1, MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_name());
        });
        btnChangePhone.setOnClickListener(view -> {
            initUIDialog(2, "0" + MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_phone());
        });
        btnChangeEmail.setOnClickListener(view -> {
            initUIDialog(3, MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_email());
        });
        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent();
            setResult(Activity.RESULT_OK, intent);
            finish();
        });
    }

    @Override
    public void updateUserTypeSuccess(Customer customer) {
        Toast.makeText(getApplicationContext(), "Cập nhật thành công!", Toast.LENGTH_LONG).show();
        setData();
    }
}