package com.example.alleat.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.alleat.Interface.ItemClickListener;
import com.example.alleat.Model.Order;
import com.example.alleat.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

     public TextView text_cart_name,text_price;
     public ImageView image_count;

     private ItemClickListener itemClickListener;

     public void setText_cart_name(TextView text_cart_name){
         this.text_cart_name=text_cart_name;
     }
    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        text_cart_name=(TextView) itemView.findViewById(R.id.cart_product_name);
        text_price=(TextView) itemView.findViewById(R.id.cart_product_price);
        image_count=(ImageView) itemView.findViewById((R.id.cart_quantity));
    }

    @Override
    public void onClick(View view) {

    }
}

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder>{
    private List<Order> listData = new ArrayList<>();
    private Context context;

    public  CartAdapter (List<Order> list,Context context) {
         this.listData = list;
        this.context=context;
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View productView = inflater.inflate(R.layout.cart,parent,false);
        return new CartViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        TextDrawable drawable = TextDrawable.builder().buildRound(""+listData.get(position).getQuantity(), Color.RED);
        holder.image_count.setImageDrawable(drawable);
        Locale locale = new Locale("he", "IL");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int price = (Integer.parseInt(listData.get(position).getPrice()))*(Integer.parseInt(listData.get(position).getQuantity()));
        holder.text_price.setText(fmt.format(price));
        holder.text_cart_name.setText(listData.get(position).getProductName());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}