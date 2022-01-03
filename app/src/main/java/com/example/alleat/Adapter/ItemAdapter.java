package com.example.alleat.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alleat.Model.Item;
import com.example.alleat.R;
import com.example.alleat.RestaurantSide.AddItemToMENU;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.AllMenuViewHolder> {

    Context context;
    List<Item> allmenuList;
    public ItemAdapter(Context context, List<Item> allmenuList) {
        this.context = context;
        this.allmenuList = allmenuList;
    }
    @NonNull
    @Override
    public AllMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new AllMenuViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AllMenuViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.allMenuName.setText(allmenuList.get(position).getName());
        holder.allMenuPrice.setText("₹ "+allmenuList.get(position).getPrice());
        holder.allMenuRating.setText(allmenuList.get(position).getRating());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AddItemToMENU.class);
                i.putExtra("name", allmenuList.get(position).getName());
                i.putExtra("price", allmenuList.get(position).getPrice());
                i.putExtra("rating", allmenuList.get(position).getRating());
                i.putExtra("image", allmenuList.get(position).getImageURL());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allmenuList.size();
    }
    public static class AllMenuViewHolder extends RecyclerView.ViewHolder{
        TextView allMenuName, allMenuNote, allMenuRating, allMenuTime, allMenuCharges, allMenuPrice;
        ImageView allMenuImage;
        public AllMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            allMenuName = itemView.findViewById(R.id.all_menu_name);
           // allMenuNote = itemView.findViewById(R.id.all_menu_note);
           // allMenuRating = itemView.findViewById(R.id.all_menu_rating);//TO DO
            allMenuPrice = itemView.findViewById(R.id.all_menu_price);
            allMenuImage = itemView.findViewById(R.id.all_menu_image);
        }
    }
}
