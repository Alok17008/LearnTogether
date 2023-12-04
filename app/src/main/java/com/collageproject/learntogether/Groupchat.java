package com.collageproject.learntogether;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class Groupchat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupchat);

        // Replace the fragment container with the GroupListFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new GroupListFragment())
                .commit();
    }
}
