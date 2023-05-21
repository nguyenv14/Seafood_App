package com.nhanlovecode.doancuoiky.Views.Personal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase.CartDatabase;
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.Cart.CartActivity;
import com.nhanlovecode.doancuoiky.Views.DressPersonal.AddDressPersonal.AddDressPersonalActivity;
import com.nhanlovecode.doancuoiky.Views.DressPersonal.ShowDressPersonal.ShowDressPersonalActivity;
import com.nhanlovecode.doancuoiky.Views.LoginAndSignup.LoginAndSignupActivity;
import com.nhanlovecode.doancuoiky.Views.Personal.Evaluate.EvaluateActivity;
import com.nhanlovecode.doancuoiky.Views.Personal.History.HistoryActivity;
import com.nhanlovecode.doancuoiky.Views.Personal.Profile.ProfileActivity;

public class PersonalPageFragment extends Fragment {
    LinearLayout btnLogOut, btnGoToHistory, btnGoToAddressActivity, btnGoToProfile, btnGoToEvaluate , loAppReviews;
    TextView tvcustomer_name;
    Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    AlertDialog alertDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_page, container, false);
        context = getActivity();
        initUI(view);
        setData();
        eventClick();
        return view;
    }

    private void setData() {
        tvcustomer_name.setText(MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_name());
    }

    private void eventClick() {
        btnLogOut.setOnClickListener(view -> {
            logout();
        });
        btnGoToHistory.setOnClickListener(view -> {
            Intent intent = new Intent(context, HistoryActivity.class);
            startActivity(intent);
        });

        btnGoToAddressActivity.setOnClickListener(view -> {
            Intent intent = new Intent(context, ShowDressPersonalActivity.class);
            startActivity(intent);
        });

        btnGoToProfile.setOnClickListener(view -> {
            Intent intent = new Intent(context, ProfileActivity.class);
            startActivityForResult(intent, Constant.KEY_UPDATE);
        });

        btnGoToEvaluate.setOnClickListener(view -> {
            Intent intent = new Intent(context, EvaluateActivity.class);
            startActivity(intent);
        });
        loAppReviews.setOnClickListener(view -> {
            Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Uri playStoreUri = Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName());
                Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, playStoreUri);
                startActivity(playStoreIntent);
            }

        });
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constant.KEY_UPDATE && resultCode == Activity.RESULT_OK){
            setData();
        }
    }


    private void logout() {
        AlertDialog.Builder buider = new AlertDialog.Builder(context).setTitle("Thông báo")
                .setMessage("Bạn muốn đăng xuất ?")
                .setPositiveButton("Có", (dialogInterface, i) -> {
                    MySharedPreferencesManager.putCustomer(Constant.PREF_KEY_CUSTOMER, null);
                    Intent intent = new Intent(context, LoginAndSignupActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(context, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                }).setNegativeButton("Không", null);
        AlertDialog alertDialog = buider.create();
        alertDialog.show();
    }

    private void initUI(View view) {
        btnLogOut = view.findViewById(R.id.btnLogOut);
        tvcustomer_name = view.findViewById(R.id.customer_name);
        btnGoToProfile = view.findViewById(R.id.btnGoToProfile);
        btnGoToHistory = view.findViewById(R.id.btnGoToHistory);
        btnGoToAddressActivity = view.findViewById(R.id.btnGoToAddressActivity);
        btnGoToEvaluate = view.findViewById(R.id.btnGoToEvaluate);
        loAppReviews = view.findViewById(R.id.loAppReviews);
    }
}