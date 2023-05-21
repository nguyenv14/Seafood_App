package com.nhanlovecode.doancuoiky.Views.Comment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Adapters.CommentAdapter;
import com.nhanlovecode.doancuoiky.Models.Comment;
import com.nhanlovecode.doancuoiky.R;

import java.util.List;

public class CommentActivity extends AppCompatActivity implements CommentMVPView{
    Toolbar toolBar;
    CommentPresenter mCommentPresenter;
    RecyclerView rcCmt;
    CommentAdapter mCommentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        mCommentPresenter = new CommentPresenter(this , this);
        initUI();
        eventClick();
        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        mCommentPresenter.getListDataCmt(intent);
    }


    private void eventClick() {
        eventClickToolbar();
    }
    private void eventClickToolbar() {
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initUI() {
        rcCmt = findViewById(R.id.rcCmt);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcCmt.setLayoutManager(linearLayoutManager);
        rcCmt.setHasFixedSize(true);
        toolBar = findViewById(R.id.toolBar);
        toolBar.setTitle("Bình Luận");
    }

    @Override
    public void getListDataCmtSuccess(List<Comment> commentList) {
        mCommentAdapter = new CommentAdapter(this, commentList );
        rcCmt.setAdapter(mCommentAdapter);
    }

    @Override
    public void getListDataCmtError() {
        Toast.makeText(this, "Có Lỗi Xảy Ra Khi Vào Phần Xem Bình Luận Sản Phẩm", Toast.LENGTH_SHORT).show();
        finish();
    }
}