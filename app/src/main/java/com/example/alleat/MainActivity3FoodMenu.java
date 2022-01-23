package com.example.alleat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.alleat.Interface.ItemClickListener;
import com.example.alleat.Model.Product;
import com.example.alleat.ViewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity3FoodMenu extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodList;

    String restaurantId="";
FirebaseRecyclerAdapter<Product, FoodViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Product");
        recyclerView= (RecyclerView) findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


if(getIntent()!=null)
    restaurantId=getIntent().getStringExtra("RestaurantId");
if(!restaurantId.isEmpty() && restaurantId != null){
    loadMenu(restaurantId);
}

    }

    private void loadMenu(String restaurantId) {
adapter = new FirebaseRecyclerAdapter<Product, FoodViewHolder>(Product.class,R.layout.food_product,FoodViewHolder.class, foodList.orderByChild("id").equalTo(restaurantId)) {
    @Override
    protected void populateViewHolder(FoodViewHolder menuViewHolder, Product product, int i) {
menuViewHolder.food_name.setText(product.getName());
        Picasso.with(getBaseContext()).load(product.getImageURL()).into(menuViewHolder.food_image);

        final Product local = product;
        menuViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Toast.makeText(MainActivity3FoodMenu.this, ""+local.getName(), Toast.LENGTH_SHORT).show();
                Intent productDescription = new Intent(MainActivity3FoodMenu.this,ProductDescription.class);
                productDescription.putExtra("ProductId",adapter.getRef(position).getKey());
                startActivity(productDescription);
            }
        });

    }
};

recyclerView.setAdapter(adapter);

    }
}