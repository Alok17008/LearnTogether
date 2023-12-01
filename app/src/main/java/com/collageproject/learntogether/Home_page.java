package com.collageproject.learntogether;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home_page extends AppCompatActivity {
    Button news,dought;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        news = findViewById(R.id.newsButton);
        dought=findViewById(R.id.GenerativeAi);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, TechnewsActivity.class);
                startActivity(intent);
            }
        });
        dought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, Generativeai.class);
                startActivity(intent);
            }
        });
    }
}
