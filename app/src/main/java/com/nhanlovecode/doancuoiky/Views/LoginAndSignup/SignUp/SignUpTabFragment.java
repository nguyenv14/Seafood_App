package com.nhanlovecode.doancuoiky.Views.LoginAndSignup.SignUp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.nhanlovecode.doancuoiky.Models.Customer;
import com.nhanlovecode.doancuoiky.Models.CustomerNew;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.LoginAndSignup.SignUp.Authentication.AuthenticationActivity;

import java.io.Serializable;

public class SignUpTabFragment extends Fragment implements SignUpTabMVPView{

    TextInputLayout layoutName, layoutEmail, layoutPass1, layoutPass2, layoutPhone;

    TextInputEditText addName, addEmail, addPass1, addPass2, addPhone;

    LinearLayout btnSignUp;
    View view;
    Context context;

    SignUpTabPresenter signUpTabPresenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign_up_tab, container, false);
        context = getActivity();
        signUpTabPresenter = new SignUpTabPresenter(context, this);
        initUI(view);
        addEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = charSequence.toString();
                if(checkEmail(text)){
                    layoutEmail.setError(null);
                }else{
                    layoutEmail.setError("Email không đúng");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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
        return view;
    }

    private void eventClick() {
        btnSignUp.setOnClickListener(view1 -> {
//            Customer customer = new Customer(addName.getText().toString(), Long.parseLong(addPhone.getText().toString()), addEmail.getText().toString(), addPass1.getText().toString());
            if(addName.getText().toString().equals("") || addPhone.getText().toString().equals("") || addEmail.getText().toString().equals("") || addPass2.getText().toString().equals("") || addPass1.getText().toString().equals("")){
                Toast.makeText(context, "Hãy nhập đầy đủ các trường!", Toast.LENGTH_SHORT).show();
            }else{
                String email = addEmail.getText().toString();
                signUpTabPresenter.sendCodeEmailCustomer(email, addName.getText().toString());
            }

        });
    }

    private void initUI(View view) {
        layoutName = view.findViewById(R.id.layoutName);
        layoutEmail = view.findViewById(R.id.layoutEmail);
        layoutPass1 = view.findViewById(R.id.layoutPass1);
        layoutPass2 = view.findViewById(R.id.layoutPass2);
        layoutPhone = view.findViewById(R.id.layoutPhone);
        addName = view.findViewById(R.id.addName);
        addEmail = view.findViewById(R.id.addEmail);
        addPass1 = view.findViewById(R.id.addPass1);
        addPass2 = view.findViewById(R.id.addPass2);
        addPhone = view.findViewById(R.id.addPhone);
        btnSignUp = view.findViewById(R.id.btnSignUp);
    }

    private boolean checkEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void getSendCodeCustomerSuccess(CustomerNew customerNew) {
        Customer customer = new Customer(addName.getText().toString(), Long.parseLong(addPhone.getText().toString()), addEmail.getText().toString(), addPass1.getText().toString());
        Intent intent = new Intent(context, AuthenticationActivity.class);
        intent.putExtra("customer" , customer);
        intent.putExtra("customerNew", customerNew);
        startActivity(intent);
    }
}