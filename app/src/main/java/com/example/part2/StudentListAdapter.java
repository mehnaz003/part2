package com.example.part2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {

    private List<Student> students = new ArrayList<>();

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_list_item, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student current = students.get(position);

        holder.nameView.setText(current.getName());
        holder.emailView.setText(current.getEmail());
        holder.userNameView.setText(current.getUserName());

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onStudentClick(students.get(position));
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onStudentLongClick(current);
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void setStudents(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    public interface OnStudentClickListener {
        void onStudentClick(Student student);
    }

    private OnStudentClickListener clickListener;

    public void setOnStudentClickListener(OnStudentClickListener listener) {
        this.clickListener = listener;
    }

    // For Feature 7 - Doing long click instead of tap to avoid conflict with Feature 6
    public interface OnStudentLongClickListener {
        void onStudentLongClick(Student student);
    }

    private OnStudentLongClickListener longClickListener;

    public void setOnStudentLongClickListener(OnStudentLongClickListener listener) {
        this.longClickListener = listener;
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView nameView, emailView, userNameView;

        StudentViewHolder(View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.student_name);
            emailView = itemView.findViewById(R.id.student_email);
            userNameView = itemView.findViewById(R.id.student_user_name);
        }
    }
}