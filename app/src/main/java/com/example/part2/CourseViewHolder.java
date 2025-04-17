package com.example.part2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CourseViewHolder extends RecyclerView.ViewHolder {
    private final TextView courseCodeTextView;
    private final TextView courseNameTextView;
    private final TextView lecturerNameTextView;

    public CourseViewHolder(View itemView) {
        super(itemView);
        courseCodeTextView = itemView.findViewById(R.id.textCourseCode);
        courseNameTextView = itemView.findViewById(R.id.textCourseName);
        lecturerNameTextView = itemView.findViewById(R.id.textLecturerName);
    }

    public void bind(Course course) {
        courseCodeTextView.setText(course.getCourseCode());
        courseNameTextView.setText(course.getCourseName());
        lecturerNameTextView.setText(course.getLecturerName());
    }

    public static CourseViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_item, parent, false);
        return new CourseViewHolder(view);
    }
}
