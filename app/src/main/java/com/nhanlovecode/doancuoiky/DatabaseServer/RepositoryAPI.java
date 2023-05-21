package com.nhanlovecode.doancuoiky.DatabaseServer;

import com.nhanlovecode.doancuoiky.Models.Cart;
import com.nhanlovecode.doancuoiky.Models.Coupon;
import com.nhanlovecode.doancuoiky.Models.Payment;
import com.nhanlovecode.doancuoiky.Models.Shipping;
import com.nhanlovecode.doancuoiky.ModelsAPI.CategoryAPI;
import com.nhanlovecode.doancuoiky.ModelsAPI.CityAPI;
import com.nhanlovecode.doancuoiky.ModelsAPI.CommentAPI;
import com.nhanlovecode.doancuoiky.ModelsAPI.CouponAPI;
import com.nhanlovecode.doancuoiky.ModelsAPI.CustomerAPI;
import com.nhanlovecode.doancuoiky.ModelsAPI.CustomerNewAPI;
import com.nhanlovecode.doancuoiky.ModelsAPI.DeliveringFeeAPI;
import com.nhanlovecode.doancuoiky.ModelsAPI.GalleryProductAPI;
import com.nhanlovecode.doancuoiky.ModelsAPI.LoginAPI;
import com.nhanlovecode.doancuoiky.ModelsAPI.OrderAPI;
import com.nhanlovecode.doancuoiky.ModelsAPI.ProductAPI;
import com.nhanlovecode.doancuoiky.ModelsAPI.ProvinceAPI;
import com.nhanlovecode.doancuoiky.ModelsAPI.SliderAPI;
import com.nhanlovecode.doancuoiky.ModelsAPI.WardsAPI;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RepositoryAPI {

    @POST("api/android/check-login")
    @FormUrlEncoded
    Observable<LoginAPI> checkLogin(
            @Field("customer_email") String customer_email,
            @Field("customer_password") String customer_password
    );

    @GET("api/android/get-slider")
    Observable<SliderAPI> getSlider();

    @GET("api/android/get-all-product")
    Observable<ProductAPI> getListAllProduct();

    @GET("api/android/get-new-product")
    Observable<ProductAPI> getNewProduct();

    @GET("api/android/get-trending-product")
    Observable<ProductAPI> getTrendingProduct();

    @GET("api/android/get-order-product")
    Observable<ProductAPI> getOrderProduct();



    @POST("api/android/get-product-by-categoryId")
    @FormUrlEncoded
    Observable<ProductAPI> getListProductByCategoryId(
            @Field("category_id") int category_id
    );

    @POST("api/android/get-product-by-category")
    @FormUrlEncoded
    Observable<ProductAPI> getProductByCategory(
            @Field("category_id") int category_id,
            @Field("product_id") int product_id
    );


    @POST("api/android/get-gallery-product")
    @FormUrlEncoded
    Observable<GalleryProductAPI> getGalleryProduct(
            @Field("product_id") int product_id
    );

    @POST("api/android/get-evaluate-product")
    @FormUrlEncoded
    Observable<CommentAPI> getCommentProduct(
            @Field("product_id") int product_id
    );

    @GET("api/android/get-category")
    Observable<CategoryAPI> getCategory();

    @GET("api/android/get-order")
    Observable<OrderAPI> getOrder(
            @Query("customer_id") int customer_id,
            @Query("order_status") int order_status
    );

    @GET("api/android/get-evaluate-order")
    Observable<OrderAPI> getEvaluateOrder(
            @Query("customer_id") int customer_id,
            @Query("order_status") int order_status
    );

    @GET("api/android/search-order")
    Observable<OrderAPI> searchOrder(
            @Query("customer_id") int customer_id,
            @Query("order_status") int order_status,
            @Query("order_code") String order_code
    );



    @GET("api/android/get-order-details")
    Observable<OrderAPI> getOrderDetails(
            @Query("order_code") String order_code
    );

    @POST("api/android/order-cancel")
    @FormUrlEncoded
    Observable<OrderAPI> cancelOrder(
            @Field("order_code") String order_code
    );

    @POST("api/android/order-receive")
    @FormUrlEncoded
    Observable<OrderAPI> receiveOrder(
            @Field("order_code") String order_code
    );
    @GET("api/android/get-evaluate-order")
    Observable<CommentAPI> getEvaluateOrder(
            @Query("order_code") String order_code
    );

    @POST("api/android/insert-evaluate-order")
    @FormUrlEncoded
    Observable<CommentAPI> insertEvaluateOrder(
            @Field("order_code") String order_code,
            @Field("comment_title") String comment_title,
            @Field("comment_content") String comment_content,
            @Field("comment_star") int comment_star);


//    ////////////////////////////////////////////////////





    @GET("api/android/get-product")
    Observable<ProductAPI> getProduct(
            @Query("page") int page
    );

//    @GET("api/android/get-category-product")
//    Observable<CategoryAPI> getCategoryProduct();



    @POST("api/android/get-product-by-search")
    @FormUrlEncoded
    Observable<ProductAPI> getListProductBySearch(
            @Field("product_name") String product_name,
            @Field("category_name") String category_name,
            @Field("filter_number") int filter_number,
            @Field("priceMin") float priceMin,
            @Field("priceMax") float priceMax
    );

    @GET("api/android/get-price-min-and-max")
    Observable<ProductAPI> getPriceMinPriceMax();

    @POST("api/android/update-user-name")
    @FormUrlEncoded
    Observable<CustomerAPI> updateUserName(
            @Field("name") String textUpdate,
            @Field("customer_id") int customer_id
    );

    @POST("api/android/update-user-phone")
    @FormUrlEncoded
    Observable<CustomerAPI> updateUserPhone(
            @Field("phone") Long phone,
            @Field("customer_id") int customer_id
    );
    @POST("api/android/update-user-email")
    @FormUrlEncoded
    Observable<CustomerAPI> updateUserEmail(
            @Field("email") String email,
            @Field("customer_id") int customer_id
    );

    @POST("api/android/update-user-pass")
    @FormUrlEncoded
    Observable<CustomerAPI> updateUserPass(
            @Field("password") String email,
            @Field("customer_id") int customer_id
    );


    @GET("api/android/get-city")
    Observable<CityAPI> getCity();

    @GET("api/android/get-province")
    Observable<ProvinceAPI> getProvince(
            @Query("name_city") String name_city
    );
    @GET("api/android/get-wards")
    Observable<WardsAPI> getWards(
            @Query("name_province") String name_province
    );

    @GET("api/android/check-coupon")
    Observable<CouponAPI> checkCoupon(
            @Query("coupon_name_code") String coupon_name_code
    );

    @GET("api/android/get-delivering-fee")
    Observable<DeliveringFeeAPI> getDeliveringFee(
            @Query("name_city") String name_city,
            @Query("name_province") String name_province,
            @Query("name_ward") String name_ward
    );

    @Multipart
    @POST("api/android/put-order")
    Observable<OrderAPI> putOrder(
            @Part("ordercode") String ordercode,
            @Part("totalprice") Double mTotalPrice,
            @Part("deliveringfee") Double mDeliveringFee,
            @Part("payment") RequestBody payment,
            @Part("shipping") RequestBody shipping,
            @Part("cart") RequestBody cart,
            @Part("coupon") RequestBody coupon
    );

    @FormUrlEncoded
    @POST("api/android/delete-order")
    Observable<OrderAPI> deleteOrder(
            @Field("order_code") String order_code,
            @Field("shipping_id") int shipping_id,
            @Field("payment_id") int payment_id);

    @FormUrlEncoded
    @POST("api/android/send-code-email-customer")
    Observable<CustomerNewAPI> sendCodeCustomerNew(
            @Field("customer_name") String customer_name,
            @Field("email") String customer_email,
            @Field("status") int status
    );

    @FormUrlEncoded
    @POST("api/android/send-code-change-pass")
    Observable<CustomerNewAPI> sendCodeChangePass(
            @Field("email") String customer_email,
            @Field("status") int status
    );

    @FormUrlEncoded
    @POST("api/android/change-user-pass")
    Observable<CustomerAPI> changePass(
            @Field("customer_email") String customer_email,
            @Field("customer_password") String customer_pass
    );


    @FormUrlEncoded
    @POST("api/android/sign-up-customer")
    Observable<CustomerAPI> signUpCustomer(
            @Field("customer_name") String customer_name,
            @Field("customer_phone") long customer_phone,
            @Field("customer_email") String customer_email,
            @Field("customer_password") String customer_password
    );


}
