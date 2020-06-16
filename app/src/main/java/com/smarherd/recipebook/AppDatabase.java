package com.smarherd.recipebook;

import androidx.room.Database;
import androidx.room.*;

@Database(entities = {Recipe.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();
}
