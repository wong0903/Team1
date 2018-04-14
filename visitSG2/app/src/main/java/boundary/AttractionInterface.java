package boundary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.wong0903.visitsg.R;

import control.AppController;
import java.util.ArrayList;
import java.util.List;

import control.AttractionManager;
import control.UserManager;
import entity.Attraction;

/**
 * Created by wong0903 on 9/4/2018.
 */

public class AttractionInterface extends AppCompatActivity implements View.OnClickListener {


    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    List<String> informationList = new ArrayList<>();
    Button btn_navigation;
    AttractionManager attractionManager = new AttractionManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_view);

        Intent i = getIntent();
        Attraction attraction = i.getExtras().getParcelable("attraction");

        Log.d("url", attraction.getApiURL());
        informationList = attractionManager.retrieveDetailedInformation(attraction.getApiURL());
        attraction.setDescription(informationList.get(6));


        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) findViewById(R.id.targetAttractionThumbnail);
        TextView name = findViewById(R.id.targetAttractionName);
        TextView address = findViewById(R.id.targetAttractionAddress);
        TextView rating = findViewById(R.id.targetAttractionOverallRating);
        TextView description = findViewById(R.id.targetAttractionDescription);
        btn_navigation = findViewById(R.id.btn_navigation);
        btn_navigation.setOnClickListener(this);

        // thumbnail image
        thumbNail.setImageUrl(attraction.getThumbnailUrl(), imageLoader);


        // title
        if (attraction.getName() != null) {
            name.setText(attraction.getName());
        }

        // address
        if (attraction.getAddress() != null) {
            address.setText(attraction.getAddress());
        }

        // description
        if (attraction.getDescription() != null) {
            description.setText(attraction.getDescription());
        }

        //overall rating
//        rating.setText(a.getOverallRating());
        rating.setText(String.valueOf(attraction.getOverallRating()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_navigation:
                String latitude = informationList.get(7);
                String longitude = informationList.get(8);
                attractionManager.getNavigation(latitude ,longitude ,getApplicationContext());
                break;
            default:
                break;
        }
    }
}
