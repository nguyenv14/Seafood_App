package com.nhanlovecode.doancuoiky.Views.DressPersonal.AddDressPersonal;

import android.content.Context;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.airbnb.lottie.parser.IntegerParser;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase.CartDatabase;
import com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase.DressPersonalDatabase;
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;
import com.nhanlovecode.doancuoiky.Models.Cart;
import com.nhanlovecode.doancuoiky.Models.DressPersonal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDressPersonalPresenter {
    Context context;
    AddDressPersonalMVPView mAddDressPersonalMVPView;

    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    RepositoryAPI mRepositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);

    public AddDressPersonalPresenter(Context context, AddDressPersonalMVPView mAddDressPersonalMVPView) {
        this.context = context;
        this.mAddDressPersonalMVPView = mAddDressPersonalMVPView;
    }

    public void getDataCity() {
        mCompositeDisposable.add(mRepositoryAPI.getCity().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(cityAPI -> {
            if (cityAPI.getStatus_code() == 200) {
                mAddDressPersonalMVPView.getDataCitySuccess(cityAPI.getData());
            }
        }, throwable -> {
            Toast.makeText(context, throwable.getMessage()+"city", Toast.LENGTH_LONG).show();
        }));
    }

    public void getDataProvince(String name_city) {
        mCompositeDisposable.add(mRepositoryAPI.getProvince(name_city).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(provinceAPI -> {
                    if (provinceAPI.getStatus_code() == 200) {
                        mAddDressPersonalMVPView.getDataProvinceSuccess(provinceAPI.getData());
                    }
                }, throwable -> {
                    Toast.makeText(context, throwable.getMessage()+"Province", Toast.LENGTH_LONG).show();
                }));
    }

    public void getDataWards(String name_province) {
        mCompositeDisposable.add(mRepositoryAPI.getWards(name_province).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(wardsAPI -> {
            if (wardsAPI.getStatus_code() == 200) {
                mAddDressPersonalMVPView.getDataWardsSuccess(wardsAPI.getData());
            }
        }, throwable -> {
            Toast.makeText(context, throwable.getMessage()+"Wards", Toast.LENGTH_LONG).show();
        }));
    }

    public void getDataDressPersonal(boolean isChecked, String nameDress, String shipping_name, String shipping_email, String shipping_phone, String citySelectedItem, String provinceSelectedItem, String wardsSelectedItem, String homeNumber) {
        if(!nameDress.isEmpty() && !shipping_name.isEmpty() && !shipping_email.isEmpty() && !shipping_phone.isEmpty() && !homeNumber.isEmpty() && !citySelectedItem.equals("Chọn thành phố") && !provinceSelectedItem.equals("Chọn quận huyện") && !wardsSelectedItem.equals("Chọn xã phường thị trấn")){
            DressPersonal dressPersonal = new DressPersonal(
                    MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_id(),
                    isChecked,
                    nameDress,
                    shipping_name,
                    shipping_email,
                    Integer.parseInt(shipping_phone),
                    citySelectedItem,
                    provinceSelectedItem,
                    wardsSelectedItem,
                    homeNumber
            );
            if (dressPersonal.getChecked()){
                List<DressPersonal> listDressPersonal = DressPersonalDatabase.getInstance(context).dressPersonalDAO().getListDressPersonal(MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_id());
                for (DressPersonal personal : listDressPersonal) {
                    if (personal.getChecked()) {
                        personal.setChecked(false);
                        DressPersonalDatabase.getInstance(context).dressPersonalDAO().UpdateDressPersonal(personal);
                    }
                }
            }
            DressPersonalDatabase.getInstance(context).dressPersonalDAO().InsertDressPersonal(dressPersonal);
            mAddDressPersonalMVPView.getDataDressPersonalSuccess();
        }else if (!checkEmail(shipping_email)){
            mAddDressPersonalMVPView.getDataDressPersonalError(Constant.KEY_TYPE_ERROR_EMAIL);
        }else if (!isValidPhone(shipping_phone.toString())){
            mAddDressPersonalMVPView.getDataDressPersonalError(Constant.KEY_TYPE_ERROR_PHONE);
        }else{
            mAddDressPersonalMVPView.getDataDressPersonalError(Constant.KEY_TYPE_ERROR_EMPTY);
        }

    }
    private boolean checkEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        // Biểu thức chính quy để kiểm tra số điện thoại hợp lệ
        String PHONE_PATTERN = "^[0-9]{10}$"; // Ví dụ: 10 chữ số
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
