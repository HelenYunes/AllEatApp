package com.example.alleat.RestaurantSide;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.alleat.Model.User;
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
    Button SignInButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        regName = (MaterialEditText) findViewById(R.id.regName);
        String sRegName = regName.getText().toString();
        password = (MaterialEditText) findViewById(R.id.password);
        phone = (MaterialEditText) findViewById(R.id.phone);
        payBox = (MaterialEditText) findViewById(R.id.payBox);
        regButton = (Button) findViewById(R.id.regButton);
        SignInButton = (Button) findViewById(R.id.SignInButton);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tableUser = database.getReference("User");
        final  ProgressDialog allEatDialog = new ProgressDialog(LoginPage.this);
        SignInButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                allEatDialog.setMessage("Please wait a few seconds...");
                                                allEatDialog.show();
                                                tableUser.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot snapshot) {
                                                        //if user exist login
                                                        if (snapshot.child(phone.getText().toString()).exists()) {
                                                            //  }
                                                            //Checking if already exists
                                                            allEatDialog.dismiss();
                                                            User user = snapshot.child(phone.getText().toString()).getValue(User.class);
                                                            //user.setName();
                                                            if (user.getPassword().equals(password.getText().toString())) {
                                                                Toast.makeText(LoginPage.this, "Sign In successfully!", Toast.LENGTH_LONG).show();
                                                                Intent restPage= new Intent(LoginPage.this, Restaurant.class);
                                                                startActivity(restPage);
                                                            } else {
                                                                Toast.makeText(LoginPage.this, "Sign In failed!", Toast.LENGTH_LONG).show();
                                                            }
                                                        } else {
                                                            allEatDialog.dismiss();
                                                            Toast.makeText(LoginPage.this, "User not exist!", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError error) {
                                                    } });  }} );
regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//final ProgressDialog allEatDialog = new ProgressDialog(LoginPage.this);
allEatDialog.setMessage("Please wait a few seconds...");
allEatDialog.show();

tableUser.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        //Checking if already exists

        //allEatDialog.dismiss();



        if (payBox.getText().toString().split("/").length != 4)
            Toast.makeText(getApplicationContext(), "bad link", Toast.LENGTH_SHORT).show();
        else {
            if (!payBox.getText().toString().split("/")[0].equals("https:"))
                Toast.makeText(getApplicationContext(), "link must be with https:", Toast.LENGTH_SHORT).show();
            else if (!payBox.getText().toString().split("/")[2].equals("payboxapp.page.link"))
                Toast.makeText(getApplicationContext(), "link not of paybox", Toast.LENGTH_SHORT).show();
            else {
                User user = new User(regName.getText().toString(), password.getText().toString());
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