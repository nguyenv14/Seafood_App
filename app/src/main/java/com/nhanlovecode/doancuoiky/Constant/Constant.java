package com.nhanlovecode.doancuoiky.Constant;

import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.Models.Cart;
import com.nhanlovecode.doancuoiky.Models.DressPersonal;
import com.nhanlovecode.doancuoiky.Models.ProductCart;

import java.util.ArrayList;
import java.util.List;

public class Constant {
    /**
     * order_status : 0 ->  Đang chờ duyệt ,  -1 ->  Đơn Hàng Bị Từ Chối ,  1  ->  Đã Duyệt, Đang Vận Chuyển , 2 -> Hoàn Thành Đơn Hàng và chưa đánh giá , 3 -> Đơn bị từ chối bởi khách hàng, 4-> Hoàn thành đơn hàng và đánh giá
     **/
    public static final String BASE_URL = "http://192.168.1.7/DoAnCNWeb/";
    public static final String MY_SHARE_PREFERENCES = "MY_SHARE_PREFERENCES";
    public static final String PREF_KEY_FIRST_INSTALL = "PREF_KEY_FIRST_INSTALL";
    public static final String PUSH_NOTIFICATION_ID = "PUSH_NOTIFICATION_ID";
    public static final String NAME_NOTIFICATION_ONE = "NOTIFICATION_ONE";
    public static final String PREF_KEY_CUSTOMER = "PREF_KEY_CUSTOMER";
    public static final int KEY_UPDATE = 1000;
    public static int PROGRESSBAR_HOME_COUNT = 0;
    public static final int TOTAL_CONTENT_HOME_COUNT = 6;
    public static final int ORDER_CODE_PROCESSING = 0;
    public static final int ORDER_CODE_DELIVERING = 1;
    public static final int ORDER_CODE_COMPLETED_NOT_YET_RATED = 2;
    public static final int ORDER_CODE_COMPLETED = 4;
    public static final int ORDER_CODE_CANCELLED_BY_CUSTOMER = 3;
    public static final int ORDER_CODE_CANCELLED_BY_ADMIN = -1;
    public static final int REQUEST_VOICE_SPEAK = 200;
    public static int CUSTOMER_ID = MySharedPreferencesManager.getCustomer(PREF_KEY_CUSTOMER).getCustomer_id();
    public static final int KEY_TYPE_ERROR_PAYMENT = 1;
    public static final int KEY_TYPE_ERROR_SHIPPING = 2;
    public static final int KEY_TYPE_ERROR_EMPTY = 0;
    public static final int KEY_TYPE_ERROR_EMAIL = 2;
    public static final int KEY_TYPE_ERROR_PHONE = 1;
    public static List<Cart> mCartListChecked = new ArrayList<>();
    public static DressPersonal mDressPersonal;
}
