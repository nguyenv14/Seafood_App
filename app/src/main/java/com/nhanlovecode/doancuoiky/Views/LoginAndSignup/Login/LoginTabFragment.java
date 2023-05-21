package com.nhanlovecode.doancuoiky.Views.LoginAndSignup.Login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.nhanlovecode.doancuoiky.Models.Customer;
import com.nhanlovecode.doancuoiky.Models.CustomerNew;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.LoginAndSignup.LoginAndSignupActivity;
import com.nhanlovecode.doancuoiky.Views.LoginAndSignup.SignUp.Authentication.AuthenticationActivity;
import com.nhanlovecode.doancuoiky.Views.Main.MainActivity;

public class LoginTabFragment extends Fragment implements LoginTabMVPView {

    TextInputEditText edtPassword , edtNameOrEmail;
    EditText edtUpdateDialog;
    TextInputLayout layoutEmail;
    Button btnLogin;
    Context context;
    View mView;
    Dialog dialog;
    TextView forgotPass, tvTypeName;

    LinearLayout btnCancelDialog, btnUpdateDialog;

    LoginTabPresenter mLoginTabPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        mLoginTabPresenter = new LoginTabPresenter(getActivity() , this);

    }

    private void eventClick() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameOrEmail = edtNameOrEmail.getText().toString();
                String passWord = edtPassword.getText().toString();
                mLoginTabPresenter.checkLogin(nameOrEmail , passWord);
            }
        });

        forgotPass.setOnClickListener(view -> {
            dialog = new Dialog(context);
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
            tvTypeName.setText("Hãy nhập email để gửi mã khôi phục");

            btnCancelDialog.setOnClickListener(view1 -> {
                dialog.dismiss();
            });

            btnUpdateDialog.setOnClickListener(view1 -> {
                if(edtUpdateDialog.getText().toString().equals("")){
                    Toast.makeText(context, "Chưa nhập email", Toast.LENGTH_SHORT).show();
                }else if(!checkEmail(edtUpdateDialog.getText().toString())){
                    Toast.makeText(context, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
                }else{
                    mLoginTabPresenter.sendCodeChangePass(edtUpdateDialog.getText().toString());
                }
//                Toast.makeText(context, edtUpdateDialog.getText().toString(), Toast.LENGTH_SHORT).show();
            });
        });
        edtNameOrEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if(checkEmail(text)){
                    layoutEmail.setError(null);
                }else{
                    layoutEmail.setError("Email không đúng");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean checkEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_login_tab, container, false);
        initUI(mView);
        eventClick();
        return mView;
    }

    private void initUI(View mView) {
        btnLogin = mView.findViewById(R.id.btnLogin);
        edtNameOrEmail = mView.findViewById(R.id.edtNameOrEmail);
        edtPassword = mView.findViewById(R.id.edtPassword);
        forgotPass = mView.findViewById(R.id.forgotPass);
        layoutEmail = mView.findViewById(R.id.layoutEmail);
    }

    @Override
    public void isValidator() {
        Toast.makeText(context, "Các Trường Không Được Để Trống", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void isLoginSuccess() {
        Intent intent = new Intent(context , MainActivity.class);
        startActivity(intent);
        Toast.makeText(context, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void isLoginError() {
        Toast.makeText(context, "Tài Khoản Hoặc Mật Khẩu Không Chính Xác", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendCodeChangePassSuccess(CustomerNew customerNew) {
        Intent intent = new Intent(context , AuthenticationActivity.class);
        intent.putExtra("customerNew", customerNew);
        intent.putExtra("customer", new Customer("nguyen", 839519415L, "nguyennvv.21it@vku.udn.vn", "123"));
        startActivity(intent);

    }


}