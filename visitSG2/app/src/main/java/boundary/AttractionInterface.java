package boundary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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


//    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    List<String> informationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_view);

        Intent i = getIntent();
        Attraction attraction = i.getExtras().getParcelable("attraction");

        AttractionManager attractionManager = new AttractionManager();
        informationList = attractionManager.retrieveDetailedInformation(attraction.getApiURL());
        attraction.setDescription(informationList.get(informationList.size()));


//        if (imageLoader == null)
//            imageLoader = AppController.getInstance().getImageLoader();
//        NetworkImageView thumbNail = (NetworkImageView)findViewById(R.id.thumbnail);
//        TextView name = findViewById(R.id.targetAttractionName);
//        TextView address = findViewById(R.id.targetAttractionAddress);
//        TextView rating = findViewById(R.id.targetAttractionOverallRating);
////        TextView operationHours = findViewById(R.id.targ);
//
//
//        // thumbnail image
//        thumbNail.setImageUrl(attraction.getThumbnailUrl(), imageLoader);
//
//        // title
//        name.setText(attraction.getName());
//
//        // address
//        address.setText(attraction.getAddress());
//
//        //overall rating
////        rating.setText(a.getOverallRating());
//        rating.setText(0);

    }
}
