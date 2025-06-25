package com.slynec.gomart.onboard;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.slynec.gomart.R;
import com.slynec.gomart.setup.signIn;

public class onboard1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard1);
        Button navigateButton = findViewById(R.id.button);
        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(onboard1.this, onboard2.class);
                startActivity(intent);
            }
        });
        TextView goToSignIn = findViewById(R.id.textView3);
        goToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(onboard1.this, signIn.class);
                startActivity(intent);
            }
        });

        TextView textView = findViewById(R.id.textView3);
        textView.setText("Skip");
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }
}