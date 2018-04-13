package boundary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.wong0903.visitsg.R;

import control.AppController;
import control.AttractionManager;
import entity.Attraction;

/**
 * Created by wong0903 on 9/4/2018.
 */

public class AttractionInterface extends AppCompatActivity {

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_view);

        Intent i = getIntent();
        String attractionURL = i.getStringExtra("attractionURL");

        AttractionManager attractionManager = new AttractionManager();
        attractionManager.retrieveDetailedInformation(attractionURL);


        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView)findViewById(R.id.thumbnail);
        TextView name = findViewById(R.id.targetAttractionName);
        TextView address = findViewById(R.id.targetAttractionAddress);
        TextView rating = findViewById(R.id.targetAttractionOverallRating);
//        TextView operationHours = findViewById(R.id.targ);

        // getting movie data for the row
        Attraction a = attractionItems.get(position);


        // thumbnail image
        thumbNail.setImageUrl(a.getThumbnailUrl(), imageLoader);

        // title
        name.setText(a.getName());

        // address
        address.setText(a.getAddress());

        //overall rating
//        rating.setText(a.getOverallRating());
        rating.setText(0);

    }
}
