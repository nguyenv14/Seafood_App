package com.nhanlovecode.doancuoiky.Views.Comment;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nhanlovecode.doancuoiky.Models.Comment;

import java.util.List;

public class CommentPresenter {
    Context context;
    CommentMVPView mCommentMVPView;

    public CommentPresenter(Context context, CommentMVPView mCommentMVPView) {
        this.context = context;
        this.mCommentMVPView = mCommentMVPView;
    }

    public void getListDataCmt(Intent intent) {
        String strComment = intent.getStringExtra("strComment");
        if (strComment == null){
            mCommentMVPView.getListDataCmtError();
        }else {
            Gson gson = new Gson();
            List<Comment> commentList = gson.fromJson(strComment, new TypeToken<List<Comment>>(){}.getType());
            mCommentMVPView.getListDataCmtSuccess(commentList);
        }
    }
}
