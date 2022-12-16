package com.example.otpverificationwithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.otpverificationwithfirebase.databinding.ActivityOtpVeryficationBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpVeryficationActivity extends AppCompatActivity {

    ActivityOtpVeryficationBinding binding;
    EditText et1, et2, et3, et4, et5, et6;
    String getbackendOtp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpVeryficationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        et1 = findViewById(R.id.inputotp1);
        et2 = findViewById(R.id.inputotp2);
        et3 = findViewById(R.id.inputotp3);
        et4 = findViewById(R.id.inputotp4);
        et5 = findViewById(R.id.inputotp5);
        et6 = findViewById(R.id.inputotp6);

        binding.txtmobileno.setText(String.format("+91-%S", getIntent().getStringExtra("mobile")));
        getbackendOtp = getIntent().getStringExtra("backendOtp");
        binding.btnSubmit.setOnClickListener(view -> {

            if (!et1.getText().toString().trim().isEmpty() &&
                    !et2.getText().toString().trim().isEmpty() &&
                    !et3.getText().toString().trim().isEmpty() &&
                    !et4.getText().toString().trim().isEmpty() &&
                    !et5.getText().toString().trim().isEmpty() &&
                    !et6.getText().toString().trim().isEmpty()) {
                //marging user's input  in a String
                String getuserotp = et1.getText().toString() +
                        et2.getText().toString() +
                        et3.getText().toString() +
                        et4.getText().toString() +
                        et5.getText().toString() +
                        et6.getText().toString();
                if (getbackendOtp != null) {
                    binding.probar2.setVisibility(View.VISIBLE);
                    binding.btnSubmit.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(getbackendOtp, getuserotp);
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(task -> {
                                binding.probar2.setVisibility(View.GONE);
                                binding.btnSubmit.setVisibility(View.VISIBLE);
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(this, "Enter Correct OTP", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(this, "Please Check Internet", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(this, "OTP Verify", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Please Enter All Numbers", Toast.LENGTH_SHORT).show();
            }
            movenumtonext();
        });
        binding.sendotpAgain.setOnClickListener(view -> {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+92" + getIntent().getStringExtra("mobile"),
                    60,
                    TimeUnit.SECONDS,
                    OtpVeryficationActivity.this,
                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            Toast.makeText(OtpVeryficationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            getbackendOtp = s;
                            Toast.makeText(OtpVeryficationActivity.this, "OTP Send Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        });
        movenumtonext();
    }

    private void movenumtonext() {
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    et2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    et3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    et4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    et5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    et6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
}