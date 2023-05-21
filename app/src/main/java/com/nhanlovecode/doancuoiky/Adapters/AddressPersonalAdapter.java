package com.nhanlovecode.doancuoiky.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nhanlovecode.doancuoiky.EventClickListener.ItemAddressClickListener;
import com.nhanlovecode.doancuoiky.Models.DressPersonal;
import com.nhanlovecode.doancuoiky.R;

import java.util.List;

public class AddressPersonalAdapter extends RecyclerView.Adapter<AddressPersonalAdapter.MyViewHolder> {
    Context context;
    List<DressPersonal> dressPersonalList;
    ItemAddressClickListener itemAddressClickListener;

    public AddressPersonalAdapter(Context context, List<DressPersonal> dressPersonalList, ItemAddressClickListener itemAddressClickListener) {
        this.context = context;
        this.dressPersonalList = dressPersonalList;
        this.itemAddressClickListener = itemAddressClickListener;
    }

    public void setData(List<DressPersonal> dressPersonalList){
        this.dressPersonalList = dressPersonalList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dress_personal, parent, false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DressPersonal dressPersonal = dressPersonalList.get(position);
        holder.nameAddress.setText(dressPersonal.getName_dress());
        holder.tvNameCustomer.setText(dressPersonal.getShipping_name() + " (" + dressPersonal.getShipping_phone() + ")");
        holder.tvAddressCustomer.setText(dressPersonal.getHome_number() + ", " + dressPersonal.getWard_name() + ", " +dressPersonal.getProvince_name() + ", " + dressPersonal.getCity_name());
        holder.switchCompat.setChecked(dressPersonal.getChecked());

        holder.switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemAddressClickListener.setClickSwitchCompat(dressPersonal);
            }
        });

        holder.btnItemAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemAddressClickListener.setClickItemAddress(dressPersonal);
            }
        });

        holder.btnDeleteAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemAddressClickListener.setClickDeleteItemAddress(dressPersonal);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (dressPersonalList == null){
            return 0;
        }
        return dressPersonalList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameAddress, tvNameCustomer, tvAddressCustomer;
        ImageView btnDeleteAddress, btnUpdateAddress;
        SwitchCompat switchCompat;
        CardView btnItemAddress;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameAddress = itemView.findViewById(R.id.nameAddress);
            tvNameCustomer = itemView.findViewById(R.id.tvNameCustomer);
            tvAddressCustomer = itemView.findViewById(R.id.tvAddressCustomer);
            btnDeleteAddress = itemView.findViewById(R.id.btnDeleteAddress);
            btnUpdateAddress = itemView.findViewById(R.id.btnUpdateAddress);
            switchCompat = itemView.findViewById(R.id.switchCompat);
            btnItemAddress = itemView.findViewById(R.id.btnItemAddress);
        }
    }
}
