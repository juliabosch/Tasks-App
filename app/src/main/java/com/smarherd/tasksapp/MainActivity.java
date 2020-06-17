package com.smarherd.tasksapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    public static ArrayList<Task> tasks = new ArrayList<>();/* = {new Email("John", "Email to John", new GregorianCalendar(2020, Calendar.JUNE, 16).getTime()),
                            new Email("Julia", "Email to Ju", new GregorianCalendar(2020, Calendar.JUNE, 16).getTime()),
                            new Phone("Albert", "Call Albert", new GregorianCalendar(2020, Calendar.JANUARY, 19).getTime())};
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        TaskAdapter taskAdapter = new TaskAdapter(this, tasks);
        recyclerView.setAdapter(taskAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_button) {
            Intent intent = new Intent(this, AddTaskActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    /*public static void setTasks(ArrayList<Task> tasks) {
        MainActivity.tasks = tasks;
    }

    public static void addTask(Task task) {
        tasks.add(task);
        setTasks(tasks);
    }*/
}