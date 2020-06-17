package com.smarherd.tasksapp;

import java.util.Date;

public class Email extends Task {

    public Email(String title, String description, Date date) {
        super(title, description, date, android.R.drawable.ic_dialog_email);
    }
}
