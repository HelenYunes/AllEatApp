package com.example.alleat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alleat.Model.Product;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProductDescription extends AppCompatActivity {
TextView product_name,product_price,product_description;
ImageView product_image;
CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;

String ProductId = "";
FirebaseDatabase database;
DatabaseReference products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);

        database = FirebaseDatabase.getInstance();
        products = database.getReference("Product");


        btnCart= (FloatingActionButton) findViewById(R.id.btnCart);
        product_image= (ImageView) findViewById(R.id.img_product);
        product_description= (TextView) findViewById(R.id.product_description);
        product_name= (TextView) findViewById(R.id.product_name);
        product_price= (TextView) findViewById(R.id.product_price);


        collapsingToolbarLayout  =(CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);

if(getIntent()!=null)
    ProductId=getIntent().getStringExtra("ProductId");
if (!ProductId.isEmpty()){
    getDetailFood(ProductId);
}
    }

    private void getDetailFood(String productId) {
        products.child(ProductId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Product product = snapshot.getValue(Product.class);

                assert product != null;
                Picasso.with(getBaseContext()).load(product.getImageURL()).into(product_image);
                collapsingToolbarLayout.setTitle(product.getName());
                product_price.setText(product.getPrice());
                product_name.setText(product.getName());
                product_description.setText(product.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}