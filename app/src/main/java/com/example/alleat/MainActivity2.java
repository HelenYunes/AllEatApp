package com.example.alleat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.alleat.Common.Common;
import com.example.alleat.Interface.ItemClickListener;
import com.example.alleat.Model.Item;
import com.example.alleat.Model.Product;
import com.example.alleat.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alleat.databinding.ActivityMain2Binding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMain2Binding binding;

    FirebaseDatabase database;
    DatabaseReference food;

    TextView txtFullName;
    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //  binding = ActivityMain2Binding.inflate(getLayoutInflater());
        //   setContentView(binding.getRoot());

        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);
        database=FirebaseDatabase.getInstance();
        food = database.getReference("Product");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        // setSupportActionBar(binding.appBarMain.toolbar);
        //  binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {

        fab.setOnClickListener(view -> {

            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        });
        // DrawerLayout drawer = binding.drawerLayout;
        // NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //   mAppBarConfiguration = new AppBarConfiguration.Builder(
        //       R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
        //       .setOpenableLayout(drawer)
        //        .build();


        // NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        //  NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        //  NavigationUI.setupWithNavController(navigationView, navController);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar,  R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        View headerView = navigationView.getHeaderView(0);
        txtFullName = (TextView) headerView.findViewById(R.id.txtFullName);
        txtFullName.setText(Common.cureentUser.getName());


        recycler_menu=(RecyclerView) findViewById(R.id.recycler_m);
        recycler_menu.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);

        loadMenu();


    }

    private void loadMenu() {
        FirebaseRecyclerAdapter<Item,MenuViewHolder>adapter=new FirebaseRecyclerAdapter<Item, MenuViewHolder>(Item.class, R.layout.menu_item, MenuViewHolder.class, food) {

            @Override
            protected void populateViewHolder(MenuViewHolder menuViewHolder, Item product, int i) {
                menuViewHolder.textMenuName.setText(product.getName());
                Picasso.with(getBaseContext()).load(product.getImageURL()).into(menuViewHolder.imageView);
                Item clickItem=product;
                menuViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(MainActivity2.this, ""+clickItem.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        };
        recycler_menu.setAdapter(adapter);

    }




    @Override
    public boolean onOptionsItemSelected (MenuItem menu) {
        return super.onOptionsItemSelected(menu);
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.nav_menu){

        }else if(id==R.id.nav_cart){

        }else if(id==R.id.nav_log_out){

        }
        DrawerLayout drawer=(DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}