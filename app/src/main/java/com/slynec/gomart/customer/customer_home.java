package com.slynec.gomart.customer;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.slynec.gomart.R;
import com.slynec.gomart.all_promotions;
import com.slynec.gomart.categories;
import com.slynec.gomart.databinding.ActivityCustomerHomeBinding;
import com.slynec.gomart.notifications;
import com.slynec.gomart.products;
import com.slynec.gomart.cart;
import com.slynec.gomart.customer_support;
import com.slynec.gomart.single_product;

public class customer_home extends AppCompatActivity {

    private ActivityCustomerHomeBinding binding;
    private DrawerLayout drawerLayout;
    private ImageView menuIcon;
    private ImageView cartIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize views here (inside onCreate)
        drawerLayout = findViewById(R.id.drawer_layout);
        menuIcon = findViewById(R.id.imageView24);
        cartIcon = findViewById(R.id.imageView25); // Assuming the cart icon has this ID

        // Toolbar setup
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // Menu icon click listener for DrawerLayout
        menuIcon.setOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }

        });

        // Initialize Navigation View
        NavigationView navigationView = findViewById(R.id.nav_view_side);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                // Handle navigation menu clicks
                if (id == R.id.product) {
                    // Open CoachListActivity
                    startActivity(new Intent(customer_home.this, products.class));
                } else if (id == R.id.promotion) {
                    startActivity(new Intent(customer_home.this, all_promotions.class));
                }else if (id == R.id.exclusives) {
                    startActivity(new Intent(customer_home.this, products.class));
                }else if (id == R.id.customer_support) {
                    startActivity(new Intent(customer_home.this, customer_support.class));
                }

                drawerLayout.closeDrawers(); // Close drawer after selection
                return true;
         }
        });




    // Cart icon click listener to navigate to Cart activity
        cartIcon.setOnClickListener(v -> {
            Intent intent = new Intent(customer_home.this, cart.class);
            startActivity(intent);
        });


        ImageView navigateToNotifications = findViewById(R.id.imageView26);
        navigateToNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(customer_home.this, notifications.class);
                startActivity(intent);
            }
        });


        // Bottom Navigation setup
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_orders)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_customer_home);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
}
