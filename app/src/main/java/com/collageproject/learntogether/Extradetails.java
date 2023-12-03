package com.collageproject.learntogether;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Extradetails extends AppCompatActivity {

    DatabaseReference databaseReference;
    EditText name,age,homeadress,town;
    Button button;
    RadioGroup gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extradetails);
        FirebaseUser mAuth = FirebaseAuth.getInstance().getCurrentUser();
        String user=mAuth.getUid();
        String mob =mAuth.getPhoneNumber();



        FirebaseDatabase mdatabase=FirebaseDatabase.getInstance();
        databaseReference=mdatabase.getReference().child("User").child(user);
        databaseReference.child("mobile no").setValue(mob);

        age=findViewById(R.id.age);
        name=findViewById(R.id.username);
        homeadress=findViewById(R.id.adress);
        town=findViewById(R.id.towncid);
        gender = (RadioGroup) findViewById( R.id.gendergroup);
        button=findViewById(R.id.Savebutton);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild("User info")){
                    Intent intent = new Intent(Extradetails.this, Home_page.class);
                    startActivity(intent);
                }else {
                    savedata();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void savedata() {
        DatabaseReference data=databaseReference.child("User info");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vie) {

                RadioButton selectedgender =findViewById( gender.getCheckedRadioButtonId() );
                String Gender = selectedgender.getText().toString();
                String Name=name.getText().toString();
                String Age=age.getText().toString();

                String Homeadress=homeadress.getText().toString();
                String Town=town.getText().toString();

                if (TextUtils.isEmpty( Name )) {
                    name.setError("Enter Name ");
                    name.requestFocus();
                    return;
                } else if (TextUtils.isEmpty( Gender )) {
                    Toast.makeText( Extradetails.this, "Please select your gender", Toast.LENGTH_SHORT ).show();
                    return;
                }else if (TextUtils.isEmpty( Age )) {
                    age.setError("Enter Age ");
                    age.requestFocus();
                    return;
                }else if (TextUtils.isEmpty( Homeadress )) {
                    homeadress.setError("");
                    homeadress.requestFocus();
                    return;
                }else if (TextUtils.isEmpty( Town )) {
                    town.setError("Enter Town ");
                    town.requestFocus();
                    return;
                }else{
                    userdetail(Name,Age,Gender,Homeadress,Town);
                }
            }
        });


    }
    private void userdetail(String name, String age,String gender, String homeadress, String town) {

        Userinfo userinfo=new Userinfo(name,age,gender,homeadress,town);
        databaseReference.child("User info").setValue(userinfo);
        Intent intent = new Intent(Extradetails.this, Home_page.class);
        startActivity(intent);
    }
}