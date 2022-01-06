
package com.example.alleat.RestaurantSide;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.alleat.Model.Item;
import com.example.alleat.Model.Product;
import com.example.alleat.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class AddItemToMENU extends AppCompatActivity {

    MaterialEditText name;
    MaterialEditText price;
    MaterialEditText id;
    MaterialEditText description;
    MaterialEditText imageURL;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        name = (MaterialEditText) findViewById(R.id.editName);
        price = (MaterialEditText) findViewById(R.id.editPrice);
        id = (MaterialEditText) findViewById(R.id.editId);
        description = (MaterialEditText) findViewById(R.id.editDesc);
        imageURL = (MaterialEditText) findViewById(R.id.editUrl);
        addButton = (Button) findViewById(R.id.editBtn);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tableProduct = database.getReference("Product");
        final ProgressDialog allEatDialog = new ProgressDialog(AddItemToMENU.this);

        tableProduct.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(id.getText().toString()).child("name").getValue() != null) {
                    String nameNew = snapshot.child(id.getText().toString()).child("name").getValue().toString();
                    name.setText(nameNew);
                }
                if (snapshot.child(id.getText().toString()).child("price").getValue() != null) {
                    String priceNew =snapshot.child(id.getText().toString()).child("price").getValue().toString();
                    price.setText(priceNew);
                }
                if (snapshot.child(id.getText().toString()).child("description").getValue()!= null) {
                    String descNew =snapshot.child(id.getText().toString()).child("description").getValue().toString();
                    description.setText(descNew);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allEatDialog.setMessage("Please wait a few seconds...");
                allEatDialog.show();
                Intent restPage = new Intent(AddItemToMENU.this, ItemPage.class);
                Bundle b = new Bundle();
                b.putString("id", id.getText().toString());
                restPage.putExtras(b);
                tableProduct.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Product item = new Product(description.getText().toString(),id.getText().toString(),imageURL.getText().toString(),name.getText().toString(), price.getText().toString());
                        tableProduct.child(id.getText().toString()).setValue(item);
                        Toast.makeText(AddItemToMENU.this, "Product added successfully!", Toast.LENGTH_LONG).show();

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                startActivity(restPage);
            }
        });

    }

}

