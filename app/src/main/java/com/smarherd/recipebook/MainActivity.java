package com.smarherd.recipebook;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Recipe> recipes;
    private static final String favoritedRecipesIdsKey = "favoritedRecipesIdsKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();

        //db.recipeDao().delete();
        recipes = db.recipeDao().getAll();

        final GridView gridView = (GridView)findViewById(R.id.gridview);
        final RecipeAdapter recipeAdapter = new RecipeAdapter(this, recipes);
        gridView.setAdapter(recipeAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                long viewId = view.getId();
                Recipe recipe = (Recipe) gridView.getItemAtPosition(position);

                if (viewId == R.id.imageview_favorite) {
                    recipe.toggleFavorite();
                    db.recipeDao().updateRecipe(recipe);
                    // This tells the GridView to redraw itself
                    // in turn calling your BooksAdapter's getView method again for each cell
                    recipeAdapter.notifyDataSetChanged();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, ViewRecipeActivity.class);
                    intent.putExtra("id", recipe.getId());
                    startActivity(intent);
                }
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, AddRecipeActivity.class);
                startActivity(intent);
            }
        });

        /*gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Recipe recipe = recipes.get(position);
                recipe.toggleFavorite();

                // This tells the GridView to redraw itself
                // in turn calling your BooksAdapter's getView method again for each cell
                recipeAdapter.notifyDataSetChanged();
            }
        });*/

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        int pointer = 0;
        // construct a list of books you've favorited
        final long[] favoritedRecipesIds = new long[recipes.size()];

        for (Recipe r : recipes) {
            if (r.getIsFavorite()) {
                favoritedRecipesIds[pointer] = r.getId();
                ++pointer;
            }
        }
        // save that list to outState for later
        outState.putLongArray(favoritedRecipesIdsKey, favoritedRecipesIds);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get our previously saved list of favorited books
        final long[] favoritedRecipesIds =
                savedInstanceState.getLongArray(favoritedRecipesIdsKey);

        // look at all of your books and figure out which are the favorites
        for (long recipeId : favoritedRecipesIds) {
            for (Recipe r: recipes) {
                if (r.getId() == recipeId) {
                    r.setIsFavorite(true);
                    break;
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}