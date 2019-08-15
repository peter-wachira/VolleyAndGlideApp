package com.volleyglide.parsejsonapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.volleyglide.parsejsonapp.R;
import com.volleyglide.parsejsonapp.adapters.RecyclerViewAdapter;
import com.volleyglide.parsejsonapp.model.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
 private final  String JSON_URL = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json";
 private RequestQueue requestQueue;
 private JsonArrayRequest request;
 private List<Anime> listAnime;
 private RecyclerView recyclerView;






 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listAnime = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);


        jsonRequest();
    }

    private void jsonRequest(){

     request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
         @Override
         public void onResponse(JSONArray response) {

             JSONObject jsonObject = null ;

             for (int i=0; i< response.length();i++){

                 try {

                     jsonObject = response.optJSONObject(i);
                     Anime anime = new Anime();
                     anime.setName(jsonObject.getString("name"));
                     anime.setDescription(jsonObject.getString("description"));
                     anime.setRating(jsonObject.getString(" Rating"));
                     anime.setCategorie(jsonObject.getString("categorie"));
                     anime.setNb_episode(jsonObject.getString("episode"));
                     anime.setStudio(jsonObject.getString("studio"));
                     anime.setImage_url(jsonObject.getString("img"));

                     listAnime.add(anime);


                 } catch (JSONException e) {
                     e.printStackTrace();
                 }

             }

             //setup recycler view
            setupRecyclerView(listAnime);

         }

     }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {

         }
     });


     Context context;
     requestQueue = Volley.newRequestQueue(MainActivity.this);
     requestQueue.add(request);

    }

    private void setupRecyclerView(List<Anime> listAnime) {
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,listAnime);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

    }

}
