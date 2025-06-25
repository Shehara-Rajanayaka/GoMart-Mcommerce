package com.slynec.gomart.setup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.slynec.gomart.R;

public class verify_account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_account);
        TextView verifyButton8 = findViewById(R.id.button8);
        verifyButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(verify_account.this, verification2.class);
                startActivity(intent);
            }
        });
    }
}