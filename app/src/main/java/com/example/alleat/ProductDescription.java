package com.example.alleat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alleat.Database.Database;
import com.example.alleat.Model.Order;
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
Product currentProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);

        database = FirebaseDatabase.getInstance();
        products = database.getReference("Product");


        btnCart= (FloatingActionButton) findViewById(R.id.btnCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Database(getBaseContext()).addToCart(new Order(
                        ProductId,currentProduct.getName(),
                        "1",currentProduct.getPrice()

                ));

                Toast.makeText(ProductDescription.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });

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
                currentProduct = snapshot.getValue(Product.class);

                assert currentProduct != null;
                Picasso.with(getBaseContext()).load(currentProduct.getImageURL()).into(product_image);
                collapsingToolbarLayout.setTitle(currentProduct.getName());
                product_price.setText(currentProduct.getPrice());
                product_name.setText(currentProduct.getName());
                product_description.setText(currentProduct.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}