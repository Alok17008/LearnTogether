package com.collageproject.learntogether;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Phonelogin extends AppCompatActivity {

    TextInputEditText editTextCountryCode;
    EditText editTextPhone;
    AppCompatButton buttonContinue;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonelogin);

        editTextCountryCode = findViewById(R.id.editTextCountryCode);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonContinue = findViewById(R.id.buttonContinue);
        mAuth = FirebaseAuth.getInstance();


        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCountryCode.getText().toString().trim();
                String number = editTextPhone.getText().toString().trim();

                if (number.isEmpty() || number.length() < 10) {
                    editTextPhone.setError("Valid number is required");
                    editTextPhone.requestFocus();
                    return;
                }

                String phoneNumber = code + number;

                Intent intent = new Intent(Phonelogin.this, VerifyPhoneActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser =mAuth.getCurrentUser();
        if(currentUser!=null){
            startActivity( new Intent(Phonelogin.this,Home_page.class) );
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AlertDialog.Builder alertDialogBuilder;

        alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Are you sure to leave ?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
