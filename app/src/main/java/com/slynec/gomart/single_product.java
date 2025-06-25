package com.slynec.gomart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class single_product extends AppCompatActivity {

    private TextView txtCounter, txtProductName, txtProductPrice, txtProductWeight,
            txtProductDiscount, txtProductDescription;
    private ImageView productImageView;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);

        // Initialize Views
        txtCounter = findViewById(R.id.txt_counter);
        ImageButton btnAdd = findViewById(R.id.btn_add);
        ImageButton btnRemove = findViewById(R.id.btn_remove);
        Button btnAddToCart = findViewById(R.id.button28);
        txtProductName = findViewById(R.id.single_product_name);
        txtProductPrice = findViewById(R.id.single_product_price);
        txtProductWeight = findViewById(R.id.single_product_weight);
        txtProductDiscount = findViewById(R.id.single_product_discount);
        txtProductDescription = findViewById(R.id.single_product_description); // New description field
        productImageView = findViewById(R.id.single_product_image);

        // Get Product Data from Intent
        Intent intent = getIntent();
        String productName = intent.getStringExtra("product_name");
        String productPrice = intent.getStringExtra("product_price");
        String productWeight = intent.getStringExtra("product_weight");
        String productDiscount = intent.getStringExtra("product_discount");
        String productDescription = intent.getStringExtra("product_description"); // Get description
        String productImage = intent.getStringExtra("product_image");

        // Set Data to Views
        txtProductName.setText(productName);
        txtProductPrice.setText("Price: " + productPrice);
        txtProductWeight.setText("Weight: " + productWeight);
        txtProductDiscount.setText("Discount: " + productDiscount);
        txtProductDescription.setText(productDescription); // Set description

        // Load Image with Glide
        Glide.with(this)
                .load(productImage)
                .into(productImageView);

        // Quantity Add/Remove Listeners
        btnAdd.setOnClickListener(v -> {
            count++;
            txtCounter.setText(String.valueOf(count));
        });

        btnRemove.setOnClickListener(v -> {
            if (count > 1) {
                count--;
                txtCounter.setText(String.valueOf(count));
            }
        });

        // Add to Cart Button Click
        btnAddToCart.setOnClickListener(view -> {
            Intent cartIntent = new Intent(single_product.this, cart.class);
            cartIntent.putExtra("product_name", productName);
            cartIntent.putExtra("product_price", productPrice);
            cartIntent.putExtra("product_weight", productWeight);
            cartIntent.putExtra("product_discount", productDiscount);
            cartIntent.putExtra("product_description", productDescription);
            cartIntent.putExtra("product_image", productImage);
            cartIntent.putExtra("quantity", count); // Pass quantity to cart
            startActivity(cartIntent);
        });
    }
}
