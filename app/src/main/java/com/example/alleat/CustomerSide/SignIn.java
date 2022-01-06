package com.example.alleat.CustomerSide;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alleat.Common.Common;
import com.example.alleat.MainActivity2;
import com.example.alleat.Model.Customer;
import com.example.alleat.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity  {
    MaterialEditText signName, phone;
    MaterialEditText password;
    Button SignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_customer);
        signName = (MaterialEditText) findViewById(R.id.regName);
        password = (MaterialEditText) findViewById(R.id.password);
        phone = (MaterialEditText) findViewById(R.id.phone_s);
        SignInButton = (Button) findViewById(R.id.SignInButton);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tableUser = database.getReference("User");

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog allEatDialog = new ProgressDialog(SignIn.this);
                allEatDialog.setMessage("Please wait a few seconds...");
                allEatDialog.show();

                tableUser.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        //if user exist login
                        if (snapshot.child(phone.getText().toString()).exists()) {
                            //Checking if already exists
                            allEatDialog.dismiss();
                            Customer user = snapshot.child(phone.getText().toString()).getValue(Customer.class);
                            //user.setName();
                            if (user.getPassword().equals(password.getText().toString())) {
                                if (user.getPassword().equals(password.getText().toString())){
                                    Toast.makeText(SignIn.this, "Sign In successfully!", Toast.LENGTH_LONG).show();

                                Intent homePage = new Intent(SignIn.this, MainActivity2.class);
                                Common.currentUser = user;
                                startActivity(homePage);
                                finish();
                            }
                                //Intent restPage = new Intent(SignIn.this, Restaurant.class);
                             //   startActivity(restPage);
                            } else {
                                Toast.makeText(SignIn.this, "Sign In failed!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            allEatDialog.dismiss();
                            Toast.makeText(SignIn.this, "User not exist!", Toast.LENGTH_SHORT).show();
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

