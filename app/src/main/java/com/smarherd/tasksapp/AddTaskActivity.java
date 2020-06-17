package com.smarherd.tasksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {

    Spinner dropdown;
    TextInputEditText titleEditText, descriptionEditText;
    EditText dateEditText;
    Button addTaskButton;
    String taskType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        titleEditText = findViewById(R.id.title_txt);
        descriptionEditText = findViewById(R.id.description_txt);
        dateEditText = findViewById(R.id.date);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        dropdown = findViewById(R.id.type);
        //String[] items = new String[]{"E-mail", "Meeting", "Phone", "To Do"};
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.task_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        taskType = dropdown.getSelectedItem().toString();
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getItemAtPosition(position).toString()) {
                    case "E-mail":
                        taskType = "E-mail";
                        break;
                    case "Phone":
                        taskType = "Phone";
                        break;
                    case "Meeting":
                        taskType = "Meeting";
                        break;
                    case "To Do":
                        taskType = "To Do";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("Nothing Selected");
            }
        });

        addTaskButton = findViewById(R.id.save_button);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String taskName = titleEditText.getText().toString();
                    String taskDescription = descriptionEditText.getText().toString();
                    Date taskDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateEditText.getText().toString());
                    switch (taskType) {
                        case "E-mail":
                            Email emailTask = new Email(taskName, taskDescription, taskDate);
                            MainActivity.tasks.add(emailTask);
                            break;
                        case "Phone":
                            Phone phoneTask = new Phone(taskName, taskDescription, taskDate);
                            MainActivity.tasks.add(phoneTask);
                            break;
                        case "Meeting":
                            Meeting meetingTask = new Meeting(taskName, taskDescription, taskDate);
                            MainActivity.tasks.add(meetingTask);
                            break;
                        case "To Do":
                            Todo toDoTask = new Todo(taskName, taskDescription, taskDate);
                            MainActivity.tasks.add(toDoTask);
                            break;
                    }
                    finish();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + "/" + (month+1) + "/" + year;
                dateEditText.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }
}