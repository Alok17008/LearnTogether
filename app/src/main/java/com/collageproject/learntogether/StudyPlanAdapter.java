package com.collageproject.learntogether;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.collageproject.learntogether.StudyPlanner;
import java.util.List;
import android.os.CountDownTimer;

    public class StudyPlanAdapter extends RecyclerView.Adapter<StudyPlanAdapter.ViewHolder> {

        private List<StudyPlan> studyPlanList;

        public StudyPlanAdapter(List<StudyPlan> studyPlanList) {
            this.studyPlanList = studyPlanList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.study_plan_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            // Reset the timer and associated views
            holder.timerTextView.setText("");
            holder.startStopButton.setText("Start");
            holder.timerIsRunning = false;

            StudyPlan studyPlan = studyPlanList.get(position);

            holder.subjectTextView.setText(studyPlan.getSubject());
            holder.timeTextView.setText(studyPlan.getTime());

            // Cancel the previous timer if any
            if (holder.timer != null) {
                holder.timer.cancel();
            }

            // Set onClickListener for the start/stop button
            holder.startStopButton.setOnClickListener(v -> {
                if (holder.timerIsRunning) {
                    // Stop the timer
                    holder.timer.cancel();
                    holder.timerIsRunning = false;
                    holder.startStopButton.setText("Start");
                } else {
                    // Start the timer
                    startTimer(holder, studyPlan);
                }
            });
        }

        private void startTimer(ViewHolder holder, StudyPlan studyPlan) {
            // Convert time to milliseconds
            long studyTimeMillis = convertTimeToMillis(studyPlan.getTime());

            // Initialize the timer
            holder.timer = new CountDownTimer(studyTimeMillis, 1000) {
                public void onTick(long millisUntilFinished) {
                    holder.timerTextView.setText(formatTime(millisUntilFinished));
                }

                public void onFinish() {
                    holder.timerTextView.setText("Time's up!");
                    holder.startStopButton.setText("Done");
                }
            };

            // Start the timer
            holder.timer.start();
            holder.timerIsRunning = true;
            holder.startStopButton.setText("Stop");
        }

        @Override
        public int getItemCount() {
            return studyPlanList.size();
        }

        private void startTimer(ViewHolder holder, long studyTimeMillis) {
            new CountDownTimer(studyTimeMillis, 1000) {
                public void onTick(long millisUntilFinished) {
                    // Update the timer TextView
                    holder.timerTextView.setText(formatTime(millisUntilFinished));
                }

                public void onFinish() {
                    // Handle timer finish (e.g., show a message, play a sound)
                    holder.timerTextView.setText("Time's up!");
                }
            }.start();
        }

        private String formatTime(long millis) {
            // Convert milliseconds to a readable time format (HH:mm:ss)
            long seconds = millis / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;

            return String.format("%02d:%02d:%02d",
                    hours % 24, minutes % 60, seconds % 60);
        }

        private long convertTimeToMillis(String time) {
            // Convert time in HH:mm format to milliseconds
            String[] parts = time.split(":");

            if (parts.length != 2) {
                // Handle the error, maybe log a message or return a default value
                return 0; // or throw an exception, depending on your requirements
            }

            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);

            return (hours * 60 + minutes) * 60 * 1000;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView subjectTextView;
            public TextView timeTextView;
            public Button startStopButton;
            public TextView timerTextView;
            public CountDownTimer timer;
            public boolean timerIsRunning;

            public ViewHolder(View view) {
                super(view);
                subjectTextView = view.findViewById(R.id.subjectTextView);
                timeTextView = view.findViewById(R.id.timeTextView);
                startStopButton = view.findViewById(R.id.startStopButton);
                timerTextView = view.findViewById(R.id.timerTextView);
                timerIsRunning = false;
            }
        }
    }
