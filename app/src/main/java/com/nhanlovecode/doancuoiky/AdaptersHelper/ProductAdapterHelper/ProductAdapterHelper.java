package com.nhanlovecode.doancuoiky.AdaptersHelper.ProductAdapterHelper;

import android.content.Context;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Adapters.ProductAdapter;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;
import com.nhanlovecode.doancuoiky.Models.Comment;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProductAdapterHelper {
    Context context;
    IProductAdapterHelper iProductAdapterHelper;

    public ProductAdapterHelper(Context context, IProductAdapterHelper iProductAdapterHelper) {
        this.context = context;
        this.iProductAdapterHelper = iProductAdapterHelper;
    }

    public void putEvaluate(ProductAdapter.MyViewHolder holder, List<Comment> commentList) {
        if (commentList.size() == 0){
            iProductAdapterHelper.getDataStarProductError(holder);
        }else {
            handleEvaluate(holder , commentList);
        }
    }

    private void handleEvaluate(ProductAdapter.MyViewHolder holder, List<Comment> commentList){
        if(commentList != null){
            int totalEval = commentList.size();
            int total_star = 0;
            Double avgStar = 0.0;
            for (Comment data : commentList) {
                total_star = total_star + data.getComment_rate_star();
            }
            avgStar = Double.valueOf(total_star / totalEval);
            avgStar = (double) Math.round(avgStar * 10) / 10; /* Làm tròn số thập phân */
            int numStars = (int) Math.round(avgStar);
            iProductAdapterHelper.getDataStarProductSuccess(holder , numStars , avgStar , totalEval);
        }
    }

    public void putProductUnit(ProductAdapter.MyViewHolder holder,String product_unit, int product_unit_sold) {
        String output = "";
        switch (product_unit) {
            case "0":
                output = "Con";
                break;
            case "1":
                output = "Phần";
                break;
            case "2":
                output = "khay";
                break;
            case "3":
                output = "Túi";
                break;
            case "4":
                output = "Kg";
                break;
            case "5":
                output = "Gam";
                break;
            case "6":
                output = "Combo";
                break;
            default:
                output = "Bug Rùi :<";
                break;
        }

        output = product_unit_sold +" "+ output;
        iProductAdapterHelper.getProductUnitSuccess(holder,output);
    }
}
