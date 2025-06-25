package com.slynec.gomart.setup;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.slynec.gomart.R;

public class reset_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        Button resetButton = findViewById(R.id.button7);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(reset_password.this, verify_account.class);
                startActivity(intent);
            }
        });

        TextView signIn = findViewById(R.id.textView19);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(reset_password.this, signIn.class);
                startActivity(intent);
            }
        });

        TextView textView = findViewById(R.id.textView19);
        textView.setText("Sign In");
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}