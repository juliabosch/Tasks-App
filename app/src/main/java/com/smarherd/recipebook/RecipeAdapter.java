package com.smarherd.recipebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Recipe> recipes;

    // 1
    public RecipeAdapter(Context context, List<Recipe> recipes) {
        this.mContext = context;
        this.recipes = recipes;
    }

    // 2
    @Override
    public int getCount() {
        return recipes.size();
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return recipes.get(position);
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1
        final Recipe recipe = recipes.get(position);

        // 2
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linearlayout_recipe, null);
        }

        // 3
        final ImageView imageView = (ImageView)convertView.findViewById(R.id.imageview_recipe_img);
        final TextView nameTextView = (TextView)convertView.findViewById(R.id.textview_recipe_name);
        final ImageView imageViewFavorite = (ImageView)convertView.findViewById(R.id.imageview_favorite);

        // 4
        String imgurl = recipe.getImg();
        Picasso.get().load(imgurl).placeholder(R.drawable.placeholder_image).error(R.drawable.placeholder_image).into(imageView);
        //imageView.setImageResource(recipe.getImageResource());
        nameTextView.setText(recipe.getName());

        imageViewFavorite.setImageResource(
                recipe.getIsFavorite() ? R.drawable.star_enabled : R.drawable.star_disabled);

        final int pos = position;
        final ViewGroup par = parent;
        ImageView favorite = (ImageView) convertView.findViewById(R.id.imageview_favorite);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GridView) par).performItemClick(v, pos, 0);
            }
        });
        return convertView;
    }

}


