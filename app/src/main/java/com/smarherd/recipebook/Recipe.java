package com.smarherd.recipebook;

import android.media.Image;

import androidx.room.*;

@Entity
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "ingredients")
    private String ingredients;

    @ColumnInfo(name = "procedure")
    private String procedure;

    @ColumnInfo(name = "image")
    private String img;

    @ColumnInfo(name = "fav")
    private boolean isFavorite = false;


 /*   public Recipe(String name, String ingredients, String procedure, String img) {
        //this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.procedure = procedure;
    }
*/
    public Recipe(String name, String ingredients, String procedure) {
        this.name = name;
        this.ingredients = ingredients;
        this.procedure = procedure;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getProcedure() {
        return procedure;
    }

    public String getImg() {
        return img;
    }

    public void toggleFavorite() {
        isFavorite = !isFavorite;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }


}
