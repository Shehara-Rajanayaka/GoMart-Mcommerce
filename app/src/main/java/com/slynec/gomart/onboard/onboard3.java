package com.slynec.gomart.onboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.slynec.gomart.R;
import com.slynec.gomart.setup.signIn;

public class onboard3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard3);

        Button navigateButton = findViewById(R.id.button6);
        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(onboard3.this, onboard2.class);
                startActivity(intent);
            }
        });

        Button getstartedButton = findViewById(R.id.button5);
        getstartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(onboard3.this, signIn.class);
                startActivity(intent);
            }
        });


    }
}