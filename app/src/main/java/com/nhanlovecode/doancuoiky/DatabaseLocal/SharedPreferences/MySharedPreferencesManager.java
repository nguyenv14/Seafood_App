package com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.nhanlovecode.doancuoiky.Models.Customer;

public class MySharedPreferencesManager {
    private static MySharedPreferencesManager instance;
    private MySharedPreferences mySharedPreferences;

    public static void init(Context context){
        instance = new MySharedPreferencesManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }
    public static MySharedPreferencesManager getInstance(){
        if (instance == null){
            instance = new MySharedPreferencesManager();
        }
        return instance;
    }

    public static void putFirstInstallApp(String key, boolean value){
        MySharedPreferencesManager.getInstance().mySharedPreferences.putBoolean(key , value);
    }

    public static boolean getFirstInstallApp(String key){
       return MySharedPreferencesManager.getInstance().mySharedPreferences.getBoolean(key);
    }

    public static void putCustomer(String key, Customer customer){
        Gson gson = new Gson();
        String strJsonCustomer = gson.toJson(customer);
        MySharedPreferencesManager.getInstance().mySharedPreferences.putString(key , strJsonCustomer);
    }
    public static Customer getCustomer(String key){
        String strJsonCustomer = MySharedPreferencesManager.getInstance().mySharedPreferences.getString(key);
        Gson gson = new Gson();
        Customer customer = gson.fromJson(strJsonCustomer , Customer.class);
        return customer;
    }


}
