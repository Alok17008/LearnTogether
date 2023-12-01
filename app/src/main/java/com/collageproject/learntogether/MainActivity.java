package com.collageproject.learntogether;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {
    private static final long SPLASH_DELAY = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the HomeActivity after the delay
                Intent intent = new Intent(MainActivity.this, Home_page.class);
                startActivity(intent);
                finish(); // Close the SplashActivity so it's not in the back stack
            }
        }, SPLASH_DELAY);

    }
}