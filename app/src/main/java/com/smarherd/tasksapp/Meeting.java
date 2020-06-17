package com.smarherd.tasksapp;

import java.util.Date;

public class Meeting extends Task {
    public Meeting(String title, String description, Date date) {
        super(title, description, date, android.R.drawable.ic_menu_agenda);
    }
}
