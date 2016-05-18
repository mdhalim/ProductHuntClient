package com.emoji.apisoup;

/**
 * Created by mdhalim on 18/05/16.
 */
    import com.loopj.android.http.AsyncHttpClient;
    import com.loopj.android.http.JsonHttpResponseHandler;
    import com.loopj.android.http.RequestParams;

public class ProductHuntClient {

    private final String url = "https://api.producthunt.com/v1/posts";

    private AsyncHttpClient client;

    public ProductHuntClient() {
        this.client = new AsyncHttpClient();
    }

    public void getProductHuntPosts(JsonHttpResponseHandler handler) {

        RequestParams params = new RequestParams();
        params.put("Accept", "application/json");
        params.put("Content-Type", "application/json");
        params.put("Authorization", "Bearer 2587aa878d7334e3c89794a6b73ebffb59a06c23b82cd0f789d2ab72d2417739");
        params.put("host", "api.producthunt.com");

        client.get(url, params, handler);
    }


}