package boundary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.wong0903.visitsg.R;

import Database.AppDatabase;
import helper.AppController;
import java.util.ArrayList;
import java.util.List;

import control.AttractionManager;
import control.RateReviewManager;
import entity.Attraction;
import entity.LoggedInUser;

/**
 * Created by wong0903 on 9/4/2018.
 * Responsible for showing the detailed information of the selected Attraction. The detailed information
 * is retrieve by calling the retrieveDetailedInformation method in the Attraction Manager.
 *
 * This class allows user to give rating and review by calling to the RateReviewInterface after clicking
 * the Rate It! button. The rate and review is done via the RateAndReview method in the RateReviewManager and
 * update the overall Rating via the calculateOverallRating method in the RateReviewManager
 * and get the direction via the getNavigation method in the AttractionManager.
 * Applied Observer Pattern in the rate and review section, the rating will be updated once a
 * new user has submitted the rating.
 */

public class AttractionInterface extends AppCompatActivity implements View.OnClickListener {

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    List<String> informationList = new ArrayList<>();
    Button btn_navigation, btn_rating;
    TextView name, address, rating, description, value,raters;
    Attraction attraction;
    LoggedInUser user;
    AppDatabase db;
    String username = "Anonymous", attractionName;
    AttractionManager attractionManager = new AttractionManager();
    RateReviewManager rateReviewManager = new RateReviewManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_view);

        db = AppDatabase.getAppDatabase(getApplicationContext());
        if(db.loggedInUserDao().getUser() != null) {
            user = db.loggedInUserDao().getUser();
            username = user.getLoginID();
        }

        Intent i = getIntent();
        attraction = i.getExtras().getParcelable("attraction");

        informationList = attractionManager.retrieveDetailedInformation(db, attraction.getApiURL());


        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) findViewById(R.id.targetAttractionThumbnail);
        name = findViewById(R.id.targetAttractionName);
        address = findViewById(R.id.targetAttractionAddress);
        rating = findViewById(R.id.targetAttractionOverallRating);
        description = findViewById(R.id.targetAttractionDescription);
        raters = findViewById(R.id.targetAttractionRaters);

        btn_navigation = findViewById(R.id.btn_navigation);
        btn_rating = findViewById(R.id.btn_rating);

        btn_navigation.setOnClickListener(this);
        btn_rating.setOnClickListener(this);

        // thumbnail image
        thumbNail.setImageUrl(attraction.getThumbnailUrl(), imageLoader);

        attractionName = attraction.getName();
        // title
        if (attractionName != null) {
            name.setText(attraction.getName());
        }

        // address
        if (attraction.getAddress() != null) {
            address.setText(attraction.getAddress());
        }

        attraction.setDescription(informationList.get(6));
        // description
        if (attraction.getDescription() != null) {
            description.setText(attraction.getDescription());
        }

        //overall rating
        attraction.setOverallRating(db.attractionDao().getOverallRatingByAttractionURL(attraction.getApiURL()));
        rating.setText(String.format("%.2f",attraction.getOverallRating()));

        attraction.setNumberOfRaters(db.attractionDao().getCountbyAttractionURL(attraction.getApiURL()));
        raters.setText(String.valueOf(attraction.getNumberOfRaters()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_navigation:
                String latitude = informationList.get(7);
                String longitude = informationList.get(8);
                attractionManager.getDirection(latitude, longitude, getApplicationContext());
                break;
            case R.id.btn_rating:
                final RateReviewInterface dialog = new RateReviewInterface(this);
                dialog.setTitle(attractionName);

                Button submitRateBtn = (Button) dialog.findViewById(R.id.submitRateBtn);
                Button cancelBtn = (Button) dialog.findViewById(R.id.cancelRateBtn);
                submitRateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                        public void onClick(View view) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    int ratings;
                                    String attractionURL,review;
                                    attractionURL = attraction.getApiURL();
                                    review = dialog.getReviewText();
                                    ratings = dialog.getRatingValue();
                                    if(rateReviewManager.verifyRating(getApplicationContext(),ratings)) {
                                        rateReviewManager.RateAndReview(db, ratings, review, attractionURL, username);
                                        attraction.setOverallRating(rateReviewManager.calculateOverallRating(db, attractionURL, ratings));
                                        rating.setText(String.format("%.2f", attraction.getOverallRating()));
                                        attraction.setNumberOfRaters(db.attractionDao().getCountbyAttractionURL(attraction.getApiURL()));
                                        raters.setText(String.valueOf(attraction.getNumberOfRaters()));
                                        dialog.dismiss();
                                    }
                                    return;
                                }
                            });
                        }
                });
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // if button is clicked, close the custom dialog
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
            default:
                break;
        }
    }

//    public Dialog getDialog(){
//        return dialog;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
