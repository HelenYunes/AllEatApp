package com.example.alleat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alleat.Common.Common;
import com.example.alleat.Database.Database;
import com.example.alleat.Model.Order;
import com.example.alleat.Model.Payment;
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
    public Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        database= FirebaseDatabase.getInstance();
        orderDetailRequest=database.getReference("Orders");

        recyclerView = (RecyclerView) findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);




        texTotalPrice =(TextView) findViewById(R.id.totalPrice);
        btnPayment = (Button) findViewById(R.id.btnPlaceOrder);

        loadListProductCart();
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(Cart.this);
                dialog.setTitle("בצע את הפעולה ");
                dialog.setContentView(R.layout.pay_rate);
                Button bt1 = (Button) dialog.findViewById(R.id.paybtn);
                Button bt2 = (Button) dialog.findViewById(R.id.rateBtn);
                RatingBar  rt1= (RatingBar) dialog.findViewById(R.id.ratingBar2);
                bt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(Intent.ACTION_VIEW);
                        intent1.setData(Uri.parse("https://payboxapp.page.link/kD8DiY3bPTwr3eMn6"));
                        startActivity(intent1);
                      //  Payment order = new Payment (Common.currentUser.getName(),editCart.getText().toString(),texTotalPrice.getText().toString(),cart);
                       // orderDetailRequest.child(String.valueOf(System.currentTimeMillis())).setValue(order);

                        new Database(getBaseContext()).cleanCart();
                        Toast.makeText(Cart.this, "Thank you for your order, enjoy your meal!", Toast.LENGTH_SHORT).show();

                        dialog.dismiss();
                        finish();
                    }
                });
                bt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bt2.setText(String.valueOf( rt1.getRating()));
                        //dialog.dismiss();
                    }
                });

                dialog.show();

                    }
                });

    }



    private void showPaymentDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cart.this);
        alertDialog.setTitle("Payment");
        alertDialog.setTitle("Enter your credit card");

        final EditText editCart = new EditText(Cart.this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        editCart.setLayoutParams(layoutParams) ;
        alertDialog.setView(editCart);
        alertDialog.setIcon(R.drawable.ic_baseline_payment_24);
        alertDialog.setPositiveButton("PAY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Payment order = new Payment (Common.currentUser.getName(),editCart.getText().toString(),texTotalPrice.getText().toString(),cart);
                orderDetailRequest.child(String.valueOf(System.currentTimeMillis())).setValue(order);

                new Database(getBaseContext()).cleanCart();
                Toast.makeText(Cart.this, "Thank you for your order, enjoy your meal!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        alertDialog.setNegativeButton("exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
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