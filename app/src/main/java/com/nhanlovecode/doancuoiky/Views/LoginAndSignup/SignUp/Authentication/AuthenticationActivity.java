package com.nhanlovecode.doancuoiky.Views.LoginAndSignup.SignUp.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.nhanlovecode.doancuoiky.Models.Customer;
import com.nhanlovecode.doancuoiky.Models.CustomerNew;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.LoginAndSignup.LoginAndSignupActivity;
import com.nhanlovecode.doancuoiky.Views.LoginAndSignup.SignUp.Authentication.ChangePass.ChangePassActivity;

public class AuthenticationActivity extends AppCompatActivity implements AuthenticationMVPViews{
    PinView pinView;
    TextView emailName, resendCode;
    LinearLayout btnSignUp;

    Customer customer;
    String code;
    CustomerNew customerNew;

    AuthenticationPresenter authenticationPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        initUI();
        authenticationPresenter = new AuthenticationPresenter(this, this);

        customer = (Customer) getIntent().getSerializableExtra("customer");
        customerNew = (CustomerNew) getIntent().getSerializableExtra("customerNew");
        getData();
        eventPinView();
        evenClick();
    }

    private void evenClick() {
        resendCode.setOnClickListener(view -> {
            AlertDialog.Builder buider = new AlertDialog.Builder(this).setTitle("Xóa")
                    .setMessage("Bạn muốn gửi lại mã code vào Email này?")
                    .setPositiveButton("Có", (dialogInterface, i) -> {
                        authenticationPresenter.sendCode(customerNew.getCustomer_email(), customer.getCustomer_name(), customerNew.getStatus());
                        getData();
                    }).setNegativeButton("Không", null);
            AlertDialog alertDialog = buider.create();
            alertDialog.show();
        });
    }

    private void getData() {
        emailName.setText(customerNew.getCustomer_email() + "");
        code = String.valueOf(customerNew.getCode());
    }

    private void eventPinView() {
        pinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().length() == 4 && charSequence.toString().equals(code)){
                    Toast.makeText(AuthenticationActivity.this, "Mã xác thực đúng!", Toast.LENGTH_SHORT).show();
                    if(customerNew.getStatus() == 0){
                        authenticationPresenter.signUp(customer);
                    }else{
                        goToChangePass();
                    }
                }else if(charSequence.toString().length() == 4 && !charSequence.toString().equals(customerNew.getCode())){
                    Toast.makeText(AuthenticationActivity.this, "Mã xác thực sai. Vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void goToChangePass() {
        Intent intent = new Intent(this, ChangePassActivity.class);
        intent.putExtra("customer_email", customerNew.getCustomer_email());
        startActivity(intent);
    }
    private void initUI() {
        pinView = findViewById(R.id.pinView);
        emailName = findViewById(R.id.emailName);
        resendCode = findViewById(R.id.resendCode);
    }
    @Override
    public void getSignUpSuccess() {
        Toast.makeText(this, "Đã đăng kí tài khoản thành công!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AuthenticationActivity.this, LoginAndSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void getReSendCodeSuccess(CustomerNew customerNew) {
        Toast.makeText(this, "Đã gửi lại mã code. Vui lòng kiểm tra", Toast.LENGTH_LONG).show();
        code = String.valueOf(customerNew.getCode());
    }
}