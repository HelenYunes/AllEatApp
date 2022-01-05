package com.example.alleat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alleat.CustomerSide.LoginPage;
import com.example.alleat.CustomerSide.SignIn;

public class SecondScreen extends AppCompatActivity {
    Button SignUpButton;
    Button ServiceRegButton2,CustomerSignButton2;
    Button SignInButton;
    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        SignUpButton = (Button) findViewById(R.id.btnSignUP);
        SignInButton = (Button) findViewById(R.id.btnSignIn);

        ServiceRegButton2 = (Button) findViewById(R.id.regButton);
        CustomerSignButton2 = (Button) findViewById(R.id.SignInButton);

        txtSlogan = (TextView) findViewById(R.id.txtSlogan);

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Reg = new Intent(SecondScreen.this, LoginPage.class);
                startActivity(Reg);

            }
        });

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign = new Intent(SecondScreen.this, SignIn.class);
                startActivity(sign);

            }
        });

    }
}