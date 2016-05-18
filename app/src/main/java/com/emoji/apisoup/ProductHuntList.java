package com.emoji.apisoup;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.io.Serializable;


/**
 * Created by mdhalim on 18/05/16.
 */

public class ProductHuntList implements Serializable {
    private static final long serialVersionUID = -8959832007991513854L;

    private String name;
    private String tagline;
    private String screenshot_url;
    private String largePosterUrl;
    private String discussion_Url;
    private String created_at;
    private int votes_count;


    public String getNames() {

        return name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getScreenshot_url() {
        return screenshot_url;
    }

    public String getDiscussion_Url() {
        return discussion_Url;
    }

    public int getVotes_count() {
        return votes_count;
    }

    public String getLargePosterUrl() {
            return largePosterUrl;
        }
    public String getTagline(){
        return tagline;
    }


    public static ProductHuntList fromJson(JSONObject jsonObject) {
        ProductHuntList b = new ProductHuntList();
        try {
            // Deserialize json into object fields
            b.name = jsonObject.getString("name");
            b.created_at = jsonObject.getString("created_at");
            b.tagline = jsonObject.getString("tagline");
            b.screenshot_url = jsonObject.getJSONObject("screenshot_url").getString("300px");
            b.largePosterUrl = jsonObject.getJSONObject("screenshot_url").getString("850px");
            b.votes_count = jsonObject.getInt("votes_count");
            b.discussion_Url = jsonObject.getString("discussion_url");


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return b;

    }

    public static ArrayList<ProductHuntList> fromJson(JSONArray jsonArray) {
        ArrayList<ProductHuntList> posts = new ArrayList<ProductHuntList>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject productJson = null;
            try {
                productJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            ProductHuntList post = ProductHuntList.fromJson(productJson);
            if (post != null)
            {
                posts.add(post);
            }

        }

        return posts;
    }

}