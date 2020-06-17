package com.smarherd.tasksapp;

import java.util.Date;

public class Todo extends Task {
    public Todo(String title, String description, Date date) {
        super(title, description, date, android.R.drawable.ic_input_get);
    }
}
