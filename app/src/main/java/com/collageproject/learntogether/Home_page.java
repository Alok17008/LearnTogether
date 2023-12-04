package com.collageproject.learntogether;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Home_page extends AppCompatActivity {
    Button news,doubt;
    LinearLayout GPT,TECHNEWS,Study,Group;
    ImageView Menu;//Logout button for now
    GoogleSignInClient gClient;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        GPT=findViewById(R.id.gptid);
        TECHNEWS=findViewById(R.id.technewsid);
        Menu=findViewById(R.id.MenuLogout);
        Study=findViewById(R.id.StudeyID);
        Group=findViewById(R.id.GroupchatID);

        Study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, StudyPlanner.class);
                startActivity(intent);
            }
        });

        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(Home_page.this, Phonelogin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        TECHNEWS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, TechnewsActivity.class);
                startActivity(intent);
            }
        });
        GPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, Generativeai.class);
                startActivity(intent);
            }
        });
        Group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, Groupchat.class);
                startActivity(intent);
            }
        });
    }
}
