package com.example.asterisk.youtubeparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.asterisk.youtubeparsing.entities.Example;
import com.example.asterisk.youtubeparsing.entities.Item;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String part = "snippet,status";
    String fieds = "nextPageToken,items(snippet(publishedAt,title,resourceId,thumbnails),status)";
    String playlistid = "PLrEnWoR732-BHrPp_Pm8_VleD68f9s14-";
    String maxresults = "50";
    String apikey = "AIzaSyAJ7XL6febb9L2PNpiiIrmrG-dJNOGfZ9g";
    List<Item> mydata = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        RequestInterface requestInterface = ApiClient.getClient().create(RequestInterface.class);
        Call<Example> call = requestInterface.getData(part,fieds,maxresults,playlistid,apikey);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                mydata = response.body().getItems();
                recyclerView.setAdapter(new  CustomAdapter(MainActivity.this,mydata));
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }
}