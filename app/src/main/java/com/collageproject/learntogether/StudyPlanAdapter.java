package com.collageproject.learntogether;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.collageproject.learntogether.StudyPlanner;
import java.util.List;

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
            StudyPlan studyPlan = studyPlanList.get(position);

            holder.subjectTextView.setText(studyPlan.getSubject());
            holder.timeTextView.setText(studyPlan.getTime());
        }

        @Override
        public int getItemCount() {
            return studyPlanList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView subjectTextView;
            public TextView timeTextView;

            public ViewHolder(View view) {
                super(view);
                subjectTextView = view.findViewById(R.id.subjectTextView);
                timeTextView = view.findViewById(R.id.timeTextView);
            }
        }
    }

