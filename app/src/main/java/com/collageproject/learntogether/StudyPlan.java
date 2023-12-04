package com.collageproject.learntogether;

public class StudyPlan {
    // StudyPlan.java

        private String subject;
        private String time;

        // Default constructor is required for Firebase deserialization
        public StudyPlan() {
        }

        public StudyPlan(String subject, String time) {
            this.subject = subject;
            this.time = time;
        }

        public String getSubject() {
            return subject;
        }

        public String getTime() {
            return time;
        }
    }

