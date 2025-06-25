package com.slynec.gomart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.slynec.gomart.customer.customer_home;

public class cart extends AppCompatActivity {

    private TextView txtCounter;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        txtCounter = findViewById(R.id.txt_counter);

        ImageButton btnAdd = findViewById(R.id.btn_add);
        ImageButton btnRemove = findViewById(R.id.btn_remove);

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


        Button navigateToProducts = findViewById(R.id.button19);
        navigateToProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cart.this, products.class);
                startActivity(intent);
            }
        });

        Button navigateToAddressConfirm = findViewById(R.id.button20);
        navigateToAddressConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cart.this, address_confirm.class);
                startActivity(intent);
            }
        });
    }
}