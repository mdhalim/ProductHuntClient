package com.emoji.apisoup;

/**
 * Created by mdhalim on 16/05/16.
 */

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpResponse;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.protocol.HTTP;






public class MainActivity extends Activity {

    private ListView lvPosts;
    private ProductHuntAdapter adapterPosts;
    public static final String POST_DETAIL_KEY = "posts";
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phunt_main);
        String serverURL = "https://api.producthunt.com/v1/posts/";
        ArrayList<ProductHuntList> aPosts = new ArrayList<ProductHuntList>();
        adapterPosts = new ProductHuntAdapter(MainActivity.this, aPosts);


        lvPosts = (ListView) findViewById(R.id.lvPosts);



        lvPosts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                {
                    Intent i = new Intent(MainActivity.this, ProductHuntDetail.class);
                    i.putExtra(POST_DETAIL_KEY, adapterPosts.getItem(position));
                    startActivity(i);
                }
            }
        });

        new LongOperation().execute(serverURL);


    }
        private class LongOperation extends AsyncTask<String, Void, Void> {

        private final HttpClient Content = new DefaultHttpClient();
        private String Error = null;
            
            @Override
            protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
 
        }
            protected Void doInBackground(String... urls){
                
                JSONArray items = null;
                
                try {

                HttpGet httpget = new HttpGet(urls[0]);
                httpget.setHeader("Accept","application/json");
                httpget.setHeader("Content-Type","application/json");
                httpget.setHeader("Authorization","Bearer 2587aa878d7334e3c89794a6b73ebffb59a06c23b82cd0f789d2ab72d2417739");
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                
                String jsonStr = Content.execute(httpget, responseHandler);
                    Log.d("Response: ", "> " + jsonStr);

                JSONObject jsonObj = new JSONObject(jsonStr);

                    items = jsonObj.getJSONArray("posts");

                    // Parse json array into array of model objects
                    ArrayList<ProductHuntList> posts = ProductHuntList.fromJson(items);

                    // Load model objects into the adapter
                    for (ProductHuntList post : posts) {
                        adapterPosts.add(post);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    Error = e.getMessage();
                    cancel(true);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {

                super.onPostExecute(result);
                // Dismiss the progress dialog
                if (pDialog.isShowing())
                    pDialog.dismiss();


                lvPosts.setAdapter(adapterPosts);
            }
        }

}
