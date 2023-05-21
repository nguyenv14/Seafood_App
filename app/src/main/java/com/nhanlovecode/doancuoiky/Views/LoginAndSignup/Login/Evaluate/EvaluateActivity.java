package com.nhanlovecode.doancuoiky.Views.LoginAndSignup.Login.Evaluate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;
import com.nhanlovecode.doancuoiky.Models.Customer;
import com.nhanlovecode.doancuoiky.Models.Order;
import com.nhanlovecode.doancuoiky.R;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class EvaluateActivity extends AppCompatActivity implements EvaluatePostMVPView {
    Toolbar toolbar;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RepositoryAPI repositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);
    TextInputEditText evaluateTitle, evaluateCotent;
    Slider evaluateQuantityStar;
    TextView btnCancel, btnPost;
    Order order;

    EvaluatePostPresenter evaluatePostPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate2);
        evaluatePostPresenter = new EvaluatePostPresenter(this, this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            order = (Order) getIntent().getSerializableExtra("order");
//        }
        Customer customer = MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER);
        initUI();
        eventClick();
    }

    private void initUI() {
        evaluateCotent = findViewById(R.id.evaluateContent);
        evaluateTitle = findViewById(R.id.evaluateTitle);
        evaluateQuantityStar = findViewById(R.id.evaluateStarQuantity);
        btnCancel = findViewById(R.id.btnCancelEvaluate);
        btnPost = findViewById(R.id.btnPostEvaluate);
        toolbar = findViewById(R.id.toolbar);
    }

    private void eventClick() {
        btnCancel.setOnClickListener(view -> {
            finish();
        });

        btnPost.setOnClickListener(view -> {
            postEvaluate();
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void postEvaluate() {
        String comment_title = String.valueOf(evaluateTitle.getText());
        String comment_content = String.valueOf(evaluateCotent.getText());
        int star_quantity = (int) evaluateQuantityStar.getValue();
        if(comment_content.isEmpty() || comment_title.isEmpty()){
            Toast.makeText(this, "Hãy nhập đầy đủ các trường", Toast.LENGTH_SHORT).show();
            return;
        }
        evaluatePostPresenter.evaluatePost(order.getOrder_code(), comment_title, comment_content, star_quantity);
    }

    @Override
    public void postEvaluateSuccess() {
        Toast.makeText(this, "Đã đăng đánh giá thành công!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}