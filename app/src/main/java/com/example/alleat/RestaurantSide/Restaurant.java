package com.example.alleat.RestaurantSide;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alleat.R;

public class Restaurant  extends AppCompatActivity {
    Button addButton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        addButton1 = (Button) findViewById(R.id.addItem);
        addButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent restPage = new Intent(Restaurant.this, AddItemToMENU.class);
               startActivity(restPage);

            }
        });
    }
}

