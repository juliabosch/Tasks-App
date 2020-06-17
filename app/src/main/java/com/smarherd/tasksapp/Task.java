package com.smarherd.tasksapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Task {

    private String title;
    private String description;
    private Date date;
    private boolean status;
    private int icon;

    public Task(String title, String description, Date date, int drawable) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.status = false;
        this.icon = drawable;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public boolean isDone() {
        return status;
    }

    public int getIcon() {
        return icon;
    }

    public String getDateText() {
        String pattern = "MM/dd/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }
}
