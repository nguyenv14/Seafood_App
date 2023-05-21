package com.nhanlovecode.doancuoiky.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemFavoriteListener;
import com.nhanlovecode.doancuoiky.Models.Favorite;
import com.nhanlovecode.doancuoiky.R;

import java.text.DecimalFormat;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    Context context;
    List<Favorite> favoriteList;

    ItemFavoriteListener itemFavoriteListener;

    public FavoriteAdapter(Context context, List<Favorite> favoriteList, ItemFavoriteListener itemFavoriteListener) {
        this.context = context;
        this.favoriteList = favoriteList;
        this.itemFavoriteListener = itemFavoriteListener;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View adapterLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder(adapterLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        Favorite favorite = favoriteList.get(position);
        Glide.with(context).load(favorite.getProduct_image()).into(holder.imgFavorite);
        holder.tvFavoriteName.setText(favorite.getProduct_name());
        holder.tvFavoritePrice.setText("Giá: " + decimalFormat.format(favorite.getProduct_price()) + " đ");
        holder.imgFavorite.setOnClickListener(view -> {
            itemFavoriteListener.onClickItemListenner(favorite);
        });
        holder.btnToSimilar.setOnClickListener(view -> {
            itemFavoriteListener.clickToSimilar(favorite);
        });
        holder.btnUnFavorite.setOnClickListener(view -> {
            itemFavoriteListener.clickUnFavorite(favorite);
        });
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder{
        ImageView imgFavorite;
        TextView tvFavoriteName, tvFavoritePrice;
        LinearLayout btnToSimilar;
        ImageView btnUnFavorite;
        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFavorite = itemView.findViewById(R.id.imgFavorite);
            tvFavoriteName = itemView.findViewById(R.id.tvFavoriteName);
            tvFavoritePrice = itemView.findViewById(R.id.tvFavoritePrice);
            btnToSimilar = itemView.findViewById(R.id.btnToSimilar);
            btnUnFavorite = itemView.findViewById(R.id.btnUnFavorite);
        }
    }
}
