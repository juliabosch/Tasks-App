package com.smarherd.tasksapp;

import java.util.Date;

public class Phone extends Task {
    public Phone(String title, String description, Date date) {
        super(title, description, date, android.R.drawable.stat_sys_speakerphone);
    }
}
