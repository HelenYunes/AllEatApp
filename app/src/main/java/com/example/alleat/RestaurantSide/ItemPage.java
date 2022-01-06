package com.example.alleat.RestaurantSide;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.alleat.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//need to be sent the id from the menu click restaurant

public class ItemPage extends AppCompatActivity {
    ImageView ImageView5;
    TextView EditItemName;
    TextView EditItemPrice;
    TextView EditItemDesc;
    String str;
    Button EditItemBtn;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference ItemsDataBase = database.getReference("Product");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
        EditItemBtn = (Button) findViewById(R.id.btnEditItem);
        EditItemName = (TextView)findViewById(R.id.editItemName);
        EditItemPrice = (TextView)findViewById(R.id.editItemPrice);
        EditItemDesc = (TextView)findViewById(R.id.editItemDesc);
        ImageView5 = (ImageView)findViewById(R.id.imageView5);


        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
        String imglink = "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300";
        Glide.with(this).load(imglink).apply(options).into(ImageView5);



        String parentID = "1202";
        Bundle bundle = getIntent().getExtras();
        if (bundle !=null)
            parentID = bundle.getString("id","1202");

        String finalParentID = parentID;


//        ItemsDataBase.child(finalParentID).child("imageURL").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String imglink = snapshot.child(finalParentID).child("imageURL").getValue().toString();
//                RequestOptions options = new RequestOptions()
//                        .centerCrop()
//                        .placeholder(R.mipmap.ic_launcher_round)
//                        .error(R.mipmap.ic_launcher_round);
//                Glide.with(ItemPage.this).load(imglink).apply(options).into(ImageView5);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        ItemsDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name =snapshot.child(finalParentID).child("name").getValue().toString();
                EditItemName.setText(name);
                String price =snapshot.child(finalParentID).child("price").getValue().toString();
                EditItemPrice.setText(price);
                String desc =snapshot.child(finalParentID).child("description").getValue().toString();
                EditItemDesc.setText(desc);

                //almost working
//                ImageView5.setImageDrawable();


                //take care of continious change in firebase
//                ImageView5.set
                //can save rating bar and etc
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//

        EditItemBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ItemPage.this, EditItemOnMenu.class);
                Bundle b = new Bundle();
                b.putString("id", finalParentID);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}
//work on edit page # almost done
// we should stay with paybox link for further development ביצוע תשלום
