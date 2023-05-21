package com.nhanlovecode.doancuoiky.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nhanlovecode.doancuoiky.Models.Slider;
import com.nhanlovecode.doancuoiky.R;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.MyViewHolder> {
    List<Slider> sliderList;
    Context context;

    public SliderAdapter( Context context , List<Slider> sliderList) {
        this.sliderList = sliderList;
        this.context = context;
    }
    public void setData(List<Slider> sliderList){
        this.sliderList = sliderList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Slider slider = sliderList.get(position);
        Glide.with(context).load(slider.getSlider_image()).into(holder.imgSlider);
    }

    @Override
    public int getItemCount() {
        return sliderList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
         ImageView imgSlider;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSlider = itemView.findViewById(R.id.imgSlider);
        }
    }
}
