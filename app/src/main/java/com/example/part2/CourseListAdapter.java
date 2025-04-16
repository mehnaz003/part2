package com.example.part2;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class CourseListAdapter extends ListAdapter<Course, CourseViewHolder> {

    public CourseListAdapter(@NonNull DiffUtil.ItemCallback<Course> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CourseViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course current = getItem(position);
        holder.bind(current);
    }

    public static class CourseDiff extends DiffUtil.ItemCallback<Course> {
        @Override
        public boolean areItemsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
            return oldItem.getCourseId() == newItem.getCourseId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
            return oldItem.getCourseCode().equals(newItem.getCourseCode()) &&
                    oldItem.getCourseName().equals(newItem.getCourseName()) &&
                    oldItem.getLecturerName().equals(newItem.getLecturerName());
        }
    }
}
