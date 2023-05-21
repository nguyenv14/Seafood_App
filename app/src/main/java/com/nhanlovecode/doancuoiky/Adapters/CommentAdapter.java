package com.nhanlovecode.doancuoiky.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhanlovecode.doancuoiky.Models.Comment;
import com.nhanlovecode.doancuoiky.R;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {
    Context context;
    List<Comment> commentList;

    public CommentAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    public void setData(List<Comment> commentList){
        this.commentList = commentList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false);
        return new CommentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.txtCommentCustomerName.setText(comment.getComment_customer_name());
        holder.txtCommentTitle.setText(comment.getComment_title());
        holder.txtCommentContent.setText(comment.getComment_content());
        holder.txtCommentRateStar.setText(comment.getComment_rate_star()+"");
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtCommentCustomerName , txtCommentTitle , txtCommentContent , txtCommentRateStar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCommentCustomerName  = itemView.findViewById(R.id.txtCommentCustomerName);
            txtCommentTitle = itemView.findViewById(R.id.txtCommentTitle);
            txtCommentContent  = itemView.findViewById(R.id.txtCommentContent);
            txtCommentRateStar = itemView.findViewById(R.id.txtCommentRateStar);
        }
    }
}
