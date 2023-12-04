package com.collageproject.learntogether;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudyPlanner extends AppCompatActivity {

    private EditText studySubjectEditText, studyTimeEditText;
    private Button addStudyPlanButton;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private RecyclerView studyPlanRecyclerView;
    private StudyPlanAdapter studyPlanAdapter;
    private List<StudyPlan> studyPlanList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_planner);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        studySubjectEditText = findViewById(R.id.studySubjectEditText);
        studyTimeEditText = findViewById(R.id.studyTimeEditText);
        addStudyPlanButton = findViewById(R.id.addStudyPlanButton);

        addStudyPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudyPlan();
            }
        });
        studyPlanRecyclerView = findViewById(R.id.studyPlanRecyclerView);
        studyPlanList = new ArrayList<>();
        studyPlanAdapter = new StudyPlanAdapter(studyPlanList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        studyPlanRecyclerView.setLayoutManager(layoutManager);
        studyPlanRecyclerView.setAdapter(studyPlanAdapter);

        // Fetch and display study plans from Firebase
        fetchStudyPlans();
    }

    private void fetchStudyPlans() {
        String userId = mAuth.getCurrentUser().getUid();
        DatabaseReference userStudyPlansRef = mDatabase.child("studyPlans").child(userId);

        userStudyPlansRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studyPlanList.clear();
                for (DataSnapshot studyPlanSnapshot : dataSnapshot.getChildren()) {
                    StudyPlan studyPlan = studyPlanSnapshot.getValue(StudyPlan.class);
                    studyPlanList.add(studyPlan);
                }
                studyPlanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StudyPlanner.this, "Failed to fetch study plans", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void addStudyPlan() {
        String subject = studySubjectEditText.getText().toString().trim();
        String time = studyTimeEditText.getText().toString().trim();

        if (subject.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        StudyPlan studyPlan = new StudyPlan(subject, time);
        String userId = mAuth.getCurrentUser().getUid();
        mDatabase.child("studyPlans").child(userId).push().setValue(studyPlan);
        Toast.makeText(this, "Study plan added successfully", Toast.LENGTH_SHORT).show();
        studySubjectEditText.setText("");
        studyTimeEditText.setText("");
    }
}

