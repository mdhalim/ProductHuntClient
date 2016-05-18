package com.emoji.apisoup;

/**
 * Created by mdhalim on 16/05/16.
 */

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;





public class MainActivity extends Activity {

    private ListView lvPosts;
    private ProductHuntAdapter adapterPosts;
    public static final String POST_DETAIL_KEY = "posts";
    private ProductHuntClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvPosts = (ListView) findViewById(R.id.lvPosts);
        ArrayList<ProductHuntList> aPosts = new ArrayList<ProductHuntList>();

        adapterPosts = new ProductHuntAdapter(this, aPosts);
        lvPosts.setAdapter(adapterPosts);

        FetchProductHuntPosts();
        setupSelectedListener();

    }


    private void FetchProductHuntPosts() {
        client = new ProductHuntClient();
        client.getProductHuntPosts(new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, JSONObject body) {
                JSONArray items = null;
                try {
                    items = body.getJSONArray("posts");

                    // Parse json array into array of model objects
                    ArrayList<ProductHuntList> posts = ProductHuntList.fromJson(items);

                    // Load model objects into the adapter
                    for (ProductHuntList post : posts) {
                        adapterPosts.addAll(post);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void setupSelectedListener() {
        lvPosts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View item, int position, long rowId) {
                Intent i = new Intent(MainActivity.this, ProductHuntDetail.class);
                i.putExtra(POST_DETAIL_KEY, adapterPosts.getItem(position));
                startActivity(i);
            }
        });
    }


}
