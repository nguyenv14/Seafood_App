package com.nhanlovecode.doancuoiky.Views.Comment;

import com.nhanlovecode.doancuoiky.Models.Comment;

import java.util.List;

public interface CommentMVPView {
    void getListDataCmtError();

    void getListDataCmtSuccess(List<Comment> commentList);
}
