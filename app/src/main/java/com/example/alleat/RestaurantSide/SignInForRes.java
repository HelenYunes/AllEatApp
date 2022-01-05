package com.example.alleat.RestaurantSide;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alleat.Common.Common;
import com.example.alleat.Common.CommonRes;
import com.example.alleat.MainActivity2;
import com.example.alleat.Model.Customer;
import com.example.alleat.Model.RestaurantUser;
import com.example.alleat.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignInForRes extends AppCompatActivity {
    MaterialEditText signName, phone;
    MaterialEditText password;
    Button SignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_res);
        signName = (MaterialEditText) findViewById(R.id.regNameRes);
        password = (MaterialEditText) findViewById(R.id.passwordRes);
        phone = (MaterialEditText) findViewById(R.id.phone_res);
        SignInButton = (Button) findViewById(R.id.SignInButtonRes);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tableUser = database.getReference("Restaurant");

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog allEatDialog = new ProgressDialog(SignInForRes.this);
                allEatDialog.setMessage("Please wait a few seconds...");
                allEatDialog.show();
                tableUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        //if user exist login
                        if (snapshot.child(phone.getText().toString()).exists()) {
                            //Checking if already exists
                            allEatDialog.dismiss();
                            RestaurantUser user = snapshot.child(phone.getText().toString()).getValue(RestaurantUser.class);
                            //user.setName();
                            if (user.getPassword().equals(password.getText().toString())) {
                                Toast.makeText(SignInForRes.this, "Sign In successfully!", Toast.LENGTH_LONG).show();

                                Intent homePage = new Intent(SignInForRes.this, Restaurant.class);
                                CommonRes.currentUser =user;
                                startActivity(homePage);
                                finish();

                                //Intent restPage = new Intent(SignIn.this, Restaurant.class);
                                //   startActivity(restPage);
                            } else {
                                Toast.makeText(SignInForRes.this, "Sign In failed!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            allEatDialog.dismiss();
                            Toast.makeText(SignInForRes.this, "User not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });
            }
        });
    }
}
