package com.example.alleat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alleat.RestaurantSide.LoginPage;

import java.lang.reflect.Type;

public class  MainActivity extends AppCompatActivity {
    Button ServiceRegButton;
    Button ServiceRegButton2,CustomerSignButton2;
    Button CustomerRegButton;
    TextView txtSlogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ServiceRegButton = (Button)findViewById(R.id.btnService);

        CustomerRegButton = (Button)findViewById(R.id.btnCustomer);

        ServiceRegButton2 = (Button)findViewById(R.id.regButton);

        CustomerSignButton2 = (Button)findViewById(R.id.SignInButton);

        txtSlogan = (TextView)findViewById(R.id.txtSlogan);

        ServiceRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Reg = new Intent(MainActivity.this, SecondScreenForRes.class);
                startActivity(Reg);

            }
        });

        CustomerRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Reg = new Intent(MainActivity.this, SecondScreen.class);
                startActivity(Reg);

            }
        });

    }
}