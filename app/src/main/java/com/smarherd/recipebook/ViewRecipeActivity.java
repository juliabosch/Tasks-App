package com.smarherd.recipebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class ViewRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);


        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Custom Recipe");

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();

        TextView nameText = findViewById(R.id.name_view);
        TextView ingredientsText = findViewById(R.id.ingredients_view);
        TextView procedureText = findViewById(R.id.procedure_view);

        Intent intent = getIntent();

        long id = intent.getLongExtra("id", 0);

        List<Recipe> recipes = db.recipeDao().getAll();

        for(Recipe r : recipes) {
            System.out.println(r.getName());
            System.out.println(r.getId());
        }

        Recipe recipe = db.recipeDao().findByName(id);

        nameText.setText(recipe.getName());
        ingredientsText.setText(recipe.getIngredients());
        procedureText.setText(recipe.getProcedure());

        /*String recipeName = intent.getExtras().getString("name");
        String recipeIngredients = intent.getExtras().getString("name");
        String recipeProcedure = intent.getExtras().getString("name");*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}