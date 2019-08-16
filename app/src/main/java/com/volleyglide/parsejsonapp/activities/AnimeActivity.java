package com.volleyglide.parsejsonapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.volleyglide.parsejsonapp.R;

import org.w3c.dom.Text;

public class AnimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);

        getSupportActionBar().hide();

        //receiving the data
        String name = getIntent().getExtras().getString("anime_name ");
        String description = getIntent().getExtras().getString("anime_description");
        String studio = getIntent().getExtras().getString("anime_studio");
        String category =getIntent().getExtras().getString("anime_category");
        int nb_episode = getIntent().getExtras().getInt("anime_nd_episode");
        String rating = getIntent().getExtras().getString("anime_rating");
        String image_url = getIntent().getExtras().getString("anime_img");

        //initializing views


        CollapsingToolbarLayout collapsingToolbarLayout =findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_name = findViewById(R.id.aa_anime_name);
        TextView  tv_studio = findViewById(R.id.aa_studio);
        TextView tv_categorie = findViewById(R.id.aa_categorie);
        TextView tv_description = findViewById(R.id.aa_description);
        TextView tv_rating = findViewById(R.id.aa_rating);
        ImageView img = findViewById(R.id.aa_thumbnail);

        //setting for all views

        tv_name.setText(name);
        tv_categorie.setText(category);
        tv_description.setText(description);
        tv_rating.setText(rating);
        tv_studio.setText(studio);

        collapsingToolbarLayout.setTitle(name);

        RequestOptions  requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape);

        //set image using glide
        Glide.with(this).load(image_url).apply(requestOptions).into(img);

    }
}
