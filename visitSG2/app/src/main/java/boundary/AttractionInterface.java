package boundary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.wong0903.visitsg.R;

import control.AppController;
import java.util.ArrayList;
import java.util.List;

import control.AttractionManager;
import entity.Attraction;

/**
 * Created by wong0903 on 9/4/2018.
 */

public class AttractionInterface extends AppCompatActivity {


    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    List<String> informationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_view);

        Intent i = getIntent();
        Attraction attraction = i.getExtras().getParcelable("attraction");

        AttractionManager attractionManager = new AttractionManager();
        Log.d("url",attraction.getApiURL());
        informationList = attractionManager.retrieveDetailedInformation(attraction.getApiURL());
        attraction.setDescription(informationList.get(informationList.size()-1));


        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView)findViewById(R.id.targetAttractionThumbnail);
        TextView name = findViewById(R.id.targetAttractionName);
        TextView address = findViewById(R.id.targetAttractionAddress);
        TextView rating = findViewById(R.id.targetAttractionOverallRating);
        TextView description = findViewById(R.id.targetAttractionDescription);
//        TextView operationHours = findViewById(R.id.targ);


        // thumbnail image
        thumbNail.setImageUrl(attraction.getThumbnailUrl(), imageLoader);

        // title
        name.setText(attraction.getName());

        // address
        address.setText(attraction.getAddress());

        // description
        description.setText(attraction.getDescription());

        //overall rating
//        rating.setText(a.getOverallRating());
        rating.setText(String.valueOf(attraction.getOverallRating()));

    }
}
