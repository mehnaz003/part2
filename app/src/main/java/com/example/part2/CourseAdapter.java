package com.example.part2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private final List<Course> courseList;

    public CourseAdapter(List<Course> courseList) {
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course_detail, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.codeView.setText(course.getCourseCode());
        holder.nameView.setText(course.getCourseName());
        holder.lecturerView.setText(course.getLecturerName());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView codeView, nameView, lecturerView;

        public CourseViewHolder(View itemView) {
            super(itemView);
            codeView = itemView.findViewById(R.id.courseCode);
            nameView = itemView.findViewById(R.id.courseName);
            lecturerView = itemView.findViewById(R.id.lecturerName);
        }
    }
}