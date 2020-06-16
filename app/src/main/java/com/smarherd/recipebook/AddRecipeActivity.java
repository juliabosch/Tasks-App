package com.smarherd.recipebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class AddRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Custom Recipe");

        final TextInputEditText name = findViewById(R.id.name_input);
        final TextInputEditText ingredients = findViewById(R.id.ingredients_input);
        final TextInputEditText procedure = findViewById(R.id.procedure_input);
        final TextInputEditText image = findViewById(R.id.image_input);
        Button button = findViewById(R.id.save_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String recipeName = name.getText().toString();
                final String recipeIngredients = ingredients.getText().toString();
                final String recipeProcedure = procedure.getText().toString();
                final String recipeImage = image.getText().toString();

                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "database-name").allowMainThreadQueries().build();

                Recipe recipe = new Recipe(recipeName, recipeIngredients, recipeProcedure);;

                long id = db.recipeDao().insertRecipe(recipe);

                Intent intent = new Intent(AddRecipeActivity.this, ViewRecipeActivity.class);

                intent.putExtra("id", id);

                startActivity(intent);
            }

        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

}

