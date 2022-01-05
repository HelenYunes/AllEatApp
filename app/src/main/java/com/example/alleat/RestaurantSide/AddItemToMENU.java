
package com.example.alleat.RestaurantSide;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.alleat.Model.Item;
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
        name = (MaterialEditText) findViewById(R.id.edit);
        price = (MaterialEditText) findViewById(R.id.edit2);
        id = (MaterialEditText) findViewById(R.id.edit5);
        id = (MaterialEditText) findViewById(R.id.edit5);
        description = (MaterialEditText) findViewById(R.id.edit4);
        imageURL = (MaterialEditText) findViewById(R.id.edit3);
        addButton = (Button) findViewById(R.id.addItem1);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tableProduct = database.getReference("Product");
        final ProgressDialog allEatDialog = new ProgressDialog(AddItemToMENU.this);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allEatDialog.setMessage("Please wait a few seconds...");
                allEatDialog.show();
                tableProduct.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Item item = new Item(id.getText().toString(),description.getText().toString(),imageURL.getText().toString(),name.getText().toString(), price.getText().toString());
                        tableProduct.child(id.getText().toString()).setValue(item);
                        //tableProduct.child(price.getText().toString()).setValue(item);
                        Toast.makeText(AddItemToMENU.this, "Product added successfully!", Toast.LENGTH_LONG).show();
                        setContentView(R.layout.item);
                        allEatDialog.dismiss();
                   //    Intent restPage = new Intent(AddItemToMENU.this, MainActivity.class);
                      //  startActivity(restPage);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });

    }

    }

