package com.example.alleat.RestaurantSide;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.alleat.Model.Customer;
import com.example.alleat.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginPage extends AppCompatActivity {

    MaterialEditText regName;
    MaterialEditText payBox;
    MaterialEditText phone;
    MaterialEditText password;
    Button regButton;
 //   Button SignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        regName = (MaterialEditText) findViewById(R.id.regName);
        password = (MaterialEditText) findViewById(R.id.password);
        phone = (MaterialEditText) findViewById(R.id.phone);
        payBox = (MaterialEditText) findViewById(R.id.payBox);
        regButton = (Button) findViewById(R.id.regButton);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tableUser = database.getReference("User");
        final  ProgressDialog allEatDialog = new ProgressDialog(LoginPage.this);

regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//final ProgressDialog allEatDialog = new ProgressDialog(LoginPage.this);
allEatDialog.setMessage("Please wait a few seconds...");
allEatDialog.show();

tableUser.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        //allEatDialog.dismiss();
        if (payBox.getText().toString().split("/").length != 4)
            Toast.makeText(getApplicationContext(), "bad link", Toast.LENGTH_SHORT).show();
        else {
            if (!payBox.getText().toString().split("/")[0].equals("https:"))
                Toast.makeText(getApplicationContext(), "link must be with https:", Toast.LENGTH_SHORT).show();
            else if (!payBox.getText().toString().split("/")[2].equals("payboxapp.page.link"))
                Toast.makeText(getApplicationContext(), "link not of paybox", Toast.LENGTH_SHORT).show();
            else {
                Customer user = new Customer(regName.getText().toString(), password.getText().toString());
                tableUser.child(phone.getText().toString()).setValue(user);
                Toast.makeText(LoginPage.this, "Register successfully!", Toast.LENGTH_LONG).show();
                //Toast.makeText(LoginPage.this, "Sign In successfully!", Toast.LENGTH_LONG).show();
                Intent restPage = new Intent(LoginPage.this, Restaurant.class);
                startActivity(restPage);
                //     b.putString("name", sRegName);
                //  intent.putExtras(b);
                //  Toast.makeText(LoginPage.this, "Hello", Toast.LENGTH_LONG).show();

                // startActivity(intent);

            }

        }
//finish();

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});
                }
            });

    }
}