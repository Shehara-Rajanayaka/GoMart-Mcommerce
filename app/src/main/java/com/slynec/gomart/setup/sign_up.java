package com.slynec.gomart.setup;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.slynec.gomart.R;

import java.util.HashMap;

public class sign_up extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    private TextInputEditText fullNameEditText, emailEditText, mobileEditText, passwordEditText, reEnterPasswordEditText;
    private TextInputLayout fullNameLayout, emailLayout, mobileLayout, passwordLayout, reEnterPasswordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth and Database Reference
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        // Find views by ID
        fullNameEditText = findViewById(R.id.textInputEditTextFullName);
        emailEditText = findViewById(R.id.textInputEditTextEmail);
        mobileEditText = findViewById(R.id.textInputEditTextMobile);
        passwordEditText = findViewById(R.id.textInputEditTextPassword);
        reEnterPasswordEditText = findViewById(R.id.textInputEditTextReEnterPassword);

        fullNameLayout = findViewById(R.id.textInputLayout7);
        emailLayout = findViewById(R.id.textInputLayout8);
        mobileLayout = findViewById(R.id.textInputLayout9);
        passwordLayout = findViewById(R.id.textInputLayout10);
        reEnterPasswordLayout = findViewById(R.id.textInputLayout11);

        findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String fullName = fullNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String mobile = mobileEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String reEnterPassword = reEnterPasswordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(fullName)) {
            fullNameLayout.setError("Full Name is required");
            return;
        }
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError("Please enter a valid email");
            return;
        }
        if (TextUtils.isEmpty(mobile) || mobile.length() < 10) {
            mobileLayout.setError("Please enter a valid mobile number");
            return;
        }
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            passwordLayout.setError("Password should be at least 6 characters");
            return;
        }
        if (!password.equals(reEnterPassword)) {
            reEnterPasswordLayout.setError("Passwords do not match");
            return;
        }

        // Create user in Firebase Auth
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            saveUserToDatabase(user.getUid(), fullName, email, mobile);
                        }
                    } else {
                        Toast.makeText(sign_up.this, "Auth Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void saveUserToDatabase(String userId, String fullName, String email, String mobile) {
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("userId", userId);
        userMap.put("fullName", fullName);
        userMap.put("email", email);
        userMap.put("mobile", mobile);

        // Debugging logs
        Log.d("FirebaseDebug", "Saving user: " + userMap.toString());

        // Save user to database
        databaseReference.child(userId).setValue(userMap).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("FirebaseDebug", "User saved successfully!");
                Toast.makeText(sign_up.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(sign_up.this, signIn.class));
                finish();
            } else {
                Log.e("FirebaseDebug", "Failed to save user: " + task.getException().getMessage());
                Toast.makeText(sign_up.this, "Database Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
