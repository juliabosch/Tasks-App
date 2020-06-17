package com.smarherd.tasksapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private View.OnClickListener onItemClickListener;
    ArrayList<Task> tasks;
    Context context;

    public TaskAdapter(Context ct, ArrayList<Task> tasks) {
        context = ct;
        this.tasks = tasks;
        //onItemClickListener = clickListener;

    }

    public void setItemClickListener(View.OnClickListener clickListener) {
        onItemClickListener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.task_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.titleTextView.setText(tasks.get(position).getTitle());
        holder.descriptionTextView.setText(tasks.get(position).getDescription());
        if (tasks.get(position).isDone())
            holder.statusTextView.setText(R.string.doneText);
        else
            holder.statusTextView.setText(R.string.toDoText);
        holder.dateTextView.setText(tasks.get(position).getDateText());
        holder.myImage.setImageResource(tasks.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        if (tasks != null) {
            if (!tasks.isEmpty())
                return tasks.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView, descriptionTextView, statusTextView, dateTextView;
        ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_textview);
            descriptionTextView = itemView.findViewById(R.id.description_textview);
            statusTextView = itemView.findViewById(R.id.status_textview);
            dateTextView = itemView.findViewById(R.id.date_textview);
            myImage = itemView.findViewById(R.id.myImageView);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }
    }
}
