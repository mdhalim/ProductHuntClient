package com.emoji.apisoup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mdhalim on 18/05/16.
 */
public class ProductHuntAdapter extends ArrayAdapter<ProductHuntList> {
    public ProductHuntAdapter(Context context, ArrayList<ProductHuntList> aPosts) {
        super(context, 0, aPosts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ProductHuntList posts = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_product_hunt, null);
        }
        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView created = (TextView) convertView.findViewById(R.id.created);
        TextView tagline = (TextView) convertView.findViewById(R.id.tagline);
        ImageView ivPosterImage = (ImageView) convertView.findViewById(R.id.ivPosterImage);

        // Populate the data into the template view using the data object
        name.setText(posts.getNames());
        created.setText("Created On: " + posts.getCreated_at() + "%");
        tagline.setText(posts.getTagline());
        Picasso.with(getContext()).load(posts.getScreenshot_url()).into(ivPosterImage);
        // Return the completed view to render on screen
        return convertView;
    }
}