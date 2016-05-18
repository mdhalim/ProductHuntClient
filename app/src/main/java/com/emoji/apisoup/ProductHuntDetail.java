package com.emoji.apisoup;

/**
 * Created by mdhalim on 18/05/16.
 */
        import android.annotation.SuppressLint;
        import android.app.Activity;
        import android.os.Bundle;
        import android.text.Html;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.squareup.picasso.Picasso;

public class ProductHuntDetail extends Activity {
    private ImageView ivPosterImage;
    private TextView name;
    private TextView discusUrl;
    private TextView upvotes;
    private TextView tagline;
    private TextView created;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_hunt_detail);
        // Fetch views
        ivPosterImage = (ImageView) findViewById(R.id.ivPosterImage);
        name = (TextView) findViewById(R.id.name);
        discusUrl = (TextView) findViewById(R.id.discusUrl);
        created = (TextView) findViewById(R.id.created);
        upvotes =  (TextView) findViewById(R.id.upvotes);
        tagline = (TextView) findViewById(R.id.tagline);
        // Load movie data
        ProductHuntList posts = (ProductHuntList) getIntent().getSerializableExtra(MainActivity.POST_DETAIL_KEY);
        loadMovie(posts);
    }

    // Populate the data for the movie
    @SuppressLint("NewApi")
    public void loadMovie(ProductHuntList posts) {
        // Populate data
        name.setText(posts.getNames());
        upvotes.setText(Html.fromHtml("<b>Upvotes:</b> " + posts.getVotes_count() + "%"));
        created.setText(posts.getCreated_at());
        tagline.setText(posts.getTagline());
        discusUrl.setText(Html.fromHtml("<b>Discussion Url:</b> " + posts.getDiscussion_Url()));
        // R.drawable.large_movie_poster from
        // http://content8.flixster.com/movie/11/15/86/11158674_pro.jpg -->
        Picasso.with(this).load(posts.getLargePosterUrl()).
                placeholder(R.drawable.large_movie_poster).
                into(ivPosterImage);
    }

}