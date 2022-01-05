package com.example.alleat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alleat.RestaurantSide.LoginPage;
import com.example.alleat.RestaurantSide.RegPageRes;
import com.example.alleat.RestaurantSide.SignIn;

public class SecondScreenForRes extends AppCompatActivity {
    Button SignUpButton;
    Button ServiceRegButton2,CustomerSignButton2;
    Button SignInButton;
    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen_res);
        SignUpButton = (Button) findViewById(R.id.btnSignUPRes);
        SignInButton = (Button) findViewById(R.id.btnSignIn);

        ServiceRegButton2 = (Button) findViewById(R.id.regButton);
        CustomerSignButton2 = (Button) findViewById(R.id.SignInButton);

        txtSlogan = (TextView) findViewById(R.id.txtSlogan);

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Reg = new Intent(SecondScreenForRes.this, RegPageRes.class);
                startActivity(Reg);

            }
        });

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign = new Intent(SecondScreenForRes.this, SignIn.class);
                startActivity(sign);

            }
        });

    }
}
