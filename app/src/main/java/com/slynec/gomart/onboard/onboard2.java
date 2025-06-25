package com.slynec.gomart.onboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.slynec.gomart.R;

public class onboard2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard2);

        Button navigateButton = findViewById(R.id.button2);
        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(onboard2.this, onboard3.class);
                startActivity(intent);
            }
        });

        Button navigateButton2 = findViewById(R.id.button3);
        navigateButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(onboard2.this, onboard1.class);
                startActivity(intent);
            }
        });
    }
}