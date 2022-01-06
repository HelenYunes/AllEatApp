package com.example.alleat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.alleat.Database.Database;
import com.example.alleat.Model.Order;
import com.example.alleat.ViewHolder.CartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference orderDetailRequest;

    TextView texTotalPrice;
    Button btnPayment;
List<Order> cart = new ArrayList<>();
CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        database= FirebaseDatabase.getInstance();
        orderDetailRequest=database.getReference("Orders");

        recyclerView = (RecyclerView) findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        texTotalPrice =(TextView) findViewById(R.id.totalPrice);
        btnPayment = (Button) findViewById(R.id.btnPlaceOrder);


        
        loadListProductCart();
    }

    private void loadListProductCart() {

        cart = new Database(this).getCarts();
        adapter = new CartAdapter(cart,this);
        recyclerView.setAdapter(adapter);
        int total =0;
        for(Order order:cart)
            total+=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));

        Locale locale = new Locale("he", "IL");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        texTotalPrice.setText(fmt.format(total));
    }
}