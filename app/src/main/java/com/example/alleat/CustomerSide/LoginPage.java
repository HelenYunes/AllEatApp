package com.example.alleat.CustomerSide;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.alleat.Common.Common;
import com.example.alleat.MainActivity2;
import com.example.alleat.Model.Customer;
import com.example.alleat.R;
import com.example.alleat.RestaurantSide.Restaurant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginPage extends AppCompatActivity {

    MaterialEditText regName;
    MaterialEditText phone;
    MaterialEditText password;
    Button regButton;
    //   Button SignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_customer);
        regName = (MaterialEditText) findViewById(R.id.regNameCustomer);
        password = (MaterialEditText) findViewById(R.id.passwordCustomer);
        phone = (MaterialEditText) findViewById(R.id.phone_customer);
        //  payBox = (MaterialEditText) findViewById(R.id.payBox);
        regButton = (Button) findViewById(R.id.SignInButtonCustomer);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tableUser = database.getReference("User");
        final ProgressDialog allEatDialog = new ProgressDialog(LoginPage.this);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//final ProgressDialog allEatDialog = new ProgressDialog(LoginPage.this);
                allEatDialog.setMessage("Please wait a few seconds...");
                allEatDialog.show();

                tableUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Customer user = new Customer(regName.getText().toString(), password.getText().toString());
                        tableUser.child(phone.getText().toString()).setValue(user);
                        Toast.makeText(LoginPage.this, "Register successfully!", Toast.LENGTH_LONG).show();
                        Intent homePage = new Intent(LoginPage.this, MainActivity2.class);
                        Common.currentUser =user;
                        startActivity(homePage);
                        finish();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}