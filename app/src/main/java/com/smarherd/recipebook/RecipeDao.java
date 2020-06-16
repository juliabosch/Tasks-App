package com.smarherd.recipebook;

import androidx.room.Dao;
import androidx.room.*;

import java.util.List;

@Dao
public interface RecipeDao {
    @Query("SELECT * FROM recipe")
    List<Recipe> getAll();

    @Query("SELECT * FROM recipe WHERE id == :identifier")
    Recipe findByName(long identifier);

    @Query("DELETE FROM recipe")
    void delete();

    /*@Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);*/

    @Insert
    void insertAll(Recipe... recipes);


    @Insert
    long insertRecipe(Recipe recipe);

    @Delete
    void delete(Recipe recipes);

    @Update
    void updateRecipe(Recipe recipe);
}
