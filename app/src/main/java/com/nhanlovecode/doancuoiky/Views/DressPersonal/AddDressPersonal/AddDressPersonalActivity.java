package com.nhanlovecode.doancuoiky.Views.DressPersonal.AddDressPersonal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.Models.City;
import com.nhanlovecode.doancuoiky.Models.Province;
import com.nhanlovecode.doancuoiky.Models.Wards;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.DressPersonal.ShowDressPersonal.ShowDressPersonalActivity;

import java.util.ArrayList;
import java.util.List;

public class AddDressPersonalActivity extends AppCompatActivity implements AddDressPersonalMVPView {
    Toolbar toolbar;
    SwitchCompat switchCompat;
    AutoCompleteTextView spinnerCity , spinnerProvince , spinnerWards;
    EditText edtNameDress , edtShippingName , edtShippingPhone , edtShippingEmail , edtHomeNumber ;
    AddDressPersonalPresenter mAddDressPersonalPresenter;
    TextView btnAddDress , btnShowDress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dress_personal);
        mAddDressPersonalPresenter = new AddDressPersonalPresenter(this , this);
        initUI();
        initSpinner();
        getData();
        eventClick();
    }

    private void eventClick() {
        eventClickToolBar();
        eventClickCity();
        eventClickProvince();
        eventClickAddDress();
        eventClickShowDress();
    }

    private void eventClickToolBar() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void eventClickShowDress() {
        btnShowDress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddDressPersonalActivity.this , ShowDressPersonalActivity.class);
                startActivity(intent);
            }
        });
    }

    private void eventClickAddDress() {
        btnAddDress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataDressPersonal();
            }
        });
    }

    private void eventClickProvince() {
        spinnerProvince.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItem = (String) adapterView.getItemAtPosition(position);
                int selectedIndex = position;
                if (!selectedItem.equals("Chọn quận huyện")){
                    mAddDressPersonalPresenter.getDataWards(selectedItem);
                }
            }
        });
    }

    private void eventClickCity() {
        spinnerCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItem = (String) adapterView.getItemAtPosition(position);
                int selectedIndex = position;
                if (!selectedItem.equals("Chọn thành phố")){
                    mAddDressPersonalPresenter.getDataProvince(selectedItem);
                }
            }
        });
    }

    private void getData() {
        mAddDressPersonalPresenter.getDataCity();

    }

    private void getDataDressPersonal() {
        boolean isChecked = switchCompat.isChecked();
        String citySelectedItem = spinnerCity.getText().toString();
        String provinceSelectedItem = spinnerProvince.getText().toString();
        String wardsSelectedItem = spinnerWards.getText().toString();
        String nameDress = edtNameDress.getText().toString();
        String shipping_name = edtShippingName.getText().toString();
        String shipping_email = edtShippingEmail.getText().toString();
        String shipping_phone = edtShippingPhone.getText().toString();
        String homeNumber = edtHomeNumber.getText().toString();

        mAddDressPersonalPresenter.getDataDressPersonal(isChecked ,nameDress,shipping_name,shipping_email
                ,shipping_phone,citySelectedItem ,provinceSelectedItem , wardsSelectedItem , homeNumber);
    }

    public void initSpinner(){
        List<String> options = new ArrayList<>();
        options.add("Chọn quận huyện");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvince.setAdapter(arrayAdapter);

        List<String> options1 = new ArrayList<>();
        options1.add("Chọn xã phường thị trấn");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWards.setAdapter(adapter);
    }
    private void initUI() {
        toolbar = findViewById(R.id.toolbar);
        switchCompat = findViewById(R.id.switchCompat);
        switchCompat.setChecked(true);
        spinnerCity = findViewById(R.id.spinnerCity);
        spinnerCity.setFocusable(false);
        spinnerProvince = findViewById(R.id.spinnerProvince);
        spinnerProvince.setFocusable(false);
        spinnerWards = findViewById(R.id.spinnerWards);
        spinnerWards.setFocusable(false);
        edtNameDress = findViewById(R.id.edtNameDress);
        edtShippingName = findViewById(R.id.edtShippingName);
        edtShippingPhone = findViewById(R.id.edtShippingPhone);
        edtShippingEmail = findViewById(R.id.edtShippingEmail);
        edtHomeNumber = findViewById(R.id.edtHomeNumber);
        btnAddDress = findViewById(R.id.btnAddDress);
        btnShowDress = findViewById(R.id.btnShowDress);
    }

    @Override
    public void getDataCitySuccess(List<City> cityList) {
                List<String> options = new ArrayList<>();
                options.add("Chọn thành phố");
                for (int i = 0; i < cityList.size(); i++) {
                    options.add(cityList.get(i).getName_city());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCity.setAdapter(adapter);
    }

    @Override
    public void getDataProvinceSuccess(List<Province> provinceList) {
        List<String> options = new ArrayList<>();
        options.add("Chọn quận huyện");
        for (int i = 0; i < provinceList.size(); i++) {
            options.add(provinceList.get(i).getName_province());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvince.setAdapter(adapter);
    }

    @Override
    public void getDataWardsSuccess(List<Wards> wardsList) {
        List<String> options = new ArrayList<>();
        options.add("Chọn xã phường thị trấn");
        for (int i = 0; i < wardsList.size(); i++) {
            options.add(wardsList.get(i).getName_ward());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWards.setAdapter(adapter);
    }

    @Override
    public void getDataDressPersonalError(int keyTypeError) {
        if (keyTypeError == Constant.KEY_TYPE_ERROR_EMPTY){
            Toast.makeText(this, "Các Trường Không Được Để Trống", Toast.LENGTH_SHORT).show();
        }else if(keyTypeError == Constant.KEY_TYPE_ERROR_PHONE){
            Toast.makeText(this, "Nhập SDT Hợp Lệ", Toast.LENGTH_SHORT).show();
        }else if(keyTypeError == Constant.KEY_TYPE_ERROR_EMAIL){
            Toast.makeText(this, "Nhập Email Hợp Lệ", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void getDataDressPersonalSuccess() {
        Toast.makeText(this, "Thêm Địa Chi Thành Công", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }


}