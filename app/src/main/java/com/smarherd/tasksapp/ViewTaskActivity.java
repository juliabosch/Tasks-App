package com.smarherd.tasksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        TextView titleText = findViewById(R.id.title_txt);
        TextView descriptionText = findViewById(R.id.description_txt);
        TextView statusText = findViewById(R.id.status_txt);
        TextView dateText = findViewById(R.id.date_txt);
        ImageView myImageView = findViewById(R.id.myImageView);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        Task task = MainActivity.tasks.get(position);

        titleText.setText(task.getTitle());
        descriptionText.setText(task.getDescription());
        if (task.isDone())
            statusText.setText(R.string.done_text);
        else statusText.setText(R.string.todo_text);
        dateText.setText(task.getDateText());
        myImageView.setImageResource(task.getIcon());
    }
}