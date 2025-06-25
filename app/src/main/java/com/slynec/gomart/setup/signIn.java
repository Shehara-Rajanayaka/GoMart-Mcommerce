package com.slynec.gomart.setup;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseTooManyRequestsException;
import com.slynec.gomart.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.FirebaseApp;
import com.slynec.gomart.customer.customer_home;

import java.util.concurrent.TimeUnit;

public class signIn extends AppCompatActivity {

    private TextInputEditText emailOrMobileEditText, passwordEditText;
    private TextInputLayout emailOrMobileInputLayout, passwordInputLayout;
    private FirebaseAuth mAuth;
    private CheckBox rememberMeCheckBox;
    private Button signInButton;
    private TextView signUpTextView, forgotPasswordTextView;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);  // Use your correct layout file

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // UI Elements
        emailOrMobileEditText = findViewById(R.id.email_or_mobile);
        passwordEditText = findViewById(R.id.password);
        emailOrMobileInputLayout = findViewById(R.id.textInputLayout6);
        passwordInputLayout = findViewById(R.id.textInputLayout5);
        rememberMeCheckBox = findViewById(R.id.checkBox);
        signInButton = findViewById(R.id.button4);
        signUpTextView = findViewById(R.id.textView14);
        forgotPasswordTextView = findViewById(R.id.textView12);

        // Sign In Button Click
        signInButton.setOnClickListener(view -> signIn());

        // SignUp Button Click
        signUpTextView.setOnClickListener(view -> {
            // Navigate to Sign Up Activity
            Intent intent = new Intent(signIn.this,sign_up.class);
            startActivity(intent);
        });

        // Forgot Password Button Click
        forgotPasswordTextView.setOnClickListener(view -> {
            // Navigate to Forgot Password Activity (if you have one)
            Intent intent = new Intent(signIn.this, reset_password.class);
            startActivity(intent);
        });
    }

    private void signIn() {
        String emailOrMobile = emailOrMobileEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validate input fields
        if (TextUtils.isEmpty(emailOrMobile)) {
            emailOrMobileInputLayout.setError("Please enter your email or mobile number.");
            return;
        } else {
            emailOrMobileInputLayout.setError(null);
        }

        if (TextUtils.isEmpty(password)) {
            passwordInputLayout.setError("Please enter your password.");
            return;
        } else {
            passwordInputLayout.setError(null);
        }

        // Check if input is an email or mobile number
        if (emailOrMobile.contains("@")) {
            signInWithEmail(emailOrMobile, password);  // Email sign-in
        } else {
            signInWithMobileNumber(emailOrMobile);  // Mobile number sign-in
        }
    }

    private void signInWithEmail(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign-in success
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // If sign-in fails, display a message
                        Toast.makeText(signIn.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void signInWithMobileNumber(String mobileNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+1" + mobileNumber, // Add country code (example: +1 for US, change according to the country)
                60, TimeUnit.SECONDS, // Timeout duration
                this, // Activity context
                new OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential credential) {
                        // Automatically verified and signed in
                        signInWithPhoneAuthCredential(credential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        // Handle error
                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(signIn.this, "Invalid phone number.", Toast.LENGTH_SHORT).show();
                        } else if (e instanceof FirebaseTooManyRequestsException) {
                            Toast.makeText(signIn.this, "Too many attempts. Please try again later.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(signIn.this, "Verification failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                        // Save the verification ID
                        signIn.this.verificationId = verificationId;
                        // Prompt user to enter the verification code
                        showVerificationDialog();
                    }
                });
    }

    private void showVerificationDialog() {
        // Show a dialog to enter the OTP sent to the mobile number
        OTPDialog otpDialog = new OTPDialog();
        otpDialog.setOnSubmitListener(verificationCode -> {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, verificationCode);
            signInWithPhoneAuthCredential(credential);
        });
        otpDialog.show(getSupportFragmentManager(), "OTPDialog");
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign-in success
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // If sign-in fails, display a message
                        Toast.makeText(signIn.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // Navigate to the HomeActivity or Dashboard screen after successful sign-in
            Intent intent = new Intent(signIn.this, customer_home.class);
            startActivity(intent);
            finish();  // Close the SignIn activity
        } else {
            Toast.makeText(signIn.this, "Sign-in failed. Please try again.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
