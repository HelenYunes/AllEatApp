package com.example.alleat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alleat.RestaurantSide.LoginPage;


public class  MainActivity extends AppCompatActivity {
    Button ServiceRegButton;
    Button ServiceRegButton2;
    TextView txtSlogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ServiceRegButton = (Button)findViewById(R.id.btnService);
        ServiceRegButton2 = (Button)findViewById(R.id.regButton);
        txtSlogan = (TextView)findViewById(R.id.txtSlogan);
        ServiceRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Reg = new Intent(MainActivity.this, LoginPage.class);
                startActivity(Reg);

            }
        });

    }
}