package com.example.otpverificationwithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.otpverificationwithfirebase.databinding.ActivityOtpNumberBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;


import java.util.concurrent.TimeUnit;

public class OtpNumber extends AppCompatActivity {
    ActivityOtpNumberBinding binding;
    EditText mobileNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mobileNumber = findViewById(R.id.input_mob_no);


        binding.btnSend.setOnClickListener(view -> {
            if (!mobileNumber.getText().toString().trim().isEmpty()) {
                if (mobileNumber.getText().toString().trim().length() == 10) {
                    binding.probar1.setVisibility(View.VISIBLE);
                    binding.btnSend.setVisibility(View.INVISIBLE);


                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + mobileNumber.getText().toString(),
                            60,
                            TimeUnit.SECONDS,
                            OtpNumber.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                    binding.probar1.setVisibility(View.GONE);
                                    binding.btnSend.setVisibility(View.VISIBLE);

                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    binding.probar1.setVisibility(View.GONE);
                                    binding.btnSend.setVisibility(View.VISIBLE);
                                    Toast.makeText(OtpNumber.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    binding.probar1.setVisibility(View.GONE);
                                    binding.btnSend.setVisibility(View.VISIBLE);
                                    Intent intent = new Intent(getApplicationContext(), OtpVeryficationActivity.class);
                                    intent.putExtra("mobile", mobileNumber.getText().toString());
                                    intent.putExtra("backendOtp", s);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                    );


                } else {
                    Toast.makeText(this, "Please Enter Correct Mobile Number...", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Enter Mobile Number...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}