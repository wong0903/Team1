package boundary;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.wong0903.visitsg.R;

import Database.AppDatabase;
import control.AppController;
import java.util.ArrayList;
import java.util.List;

import control.AttractionManager;
import control.RateReviewManager;
import control.UserManager;
import entity.Attraction;
import entity.LoggedInUser;
import entity.RatingAndReview;
import entity.User;

/**
 * Created by wong0903 on 9/4/2018.
 */

public class AttractionInterface extends AppCompatActivity implements View.OnClickListener {

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    List<String> informationList = new ArrayList<>();
    Button btn_navigation, btn_rating;
    TextView name;
    Attraction attraction;
    LoggedInUser user;
    AttractionManager attractionManager = new AttractionManager();
    RateReviewManager rateReviewManager = new RateReviewManager();
    AppDatabase db;
    TextView rating;
    String username = "Anonymous";
    private String attractionName;
    TextView value;
    RatingBar ratingBar;
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

        informationList = attractionManager.retrieveDetailedInformation(db ,attraction.getApiURL());
        attraction.setDescription(informationList.get(6));
        attraction.setOverallRating(Double.parseDouble(informationList.get(informationList.size()-1)));


        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) findViewById(R.id.targetAttractionThumbnail);
        name = findViewById(R.id.targetAttractionName);
        TextView address = findViewById(R.id.targetAttractionAddress);
        rating = findViewById(R.id.targetAttractionOverallRating);
        TextView description = findViewById(R.id.targetAttractionDescription);

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

        // description
        if (attraction.getDescription() != null) {
            description.setText(attraction.getDescription());
        }

        //overall rating
        rating.setText(String.valueOf(attraction.getOverallRating()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_navigation:
                String latitude = informationList.get(7);
                String longitude = informationList.get(8);
                attractionManager.getNavigation(latitude, longitude, getApplicationContext());
                break;
            case R.id.btn_rating:
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.ratingbox);
                ratingBar = (RatingBar)dialog.findViewById(R.id.ratingsBar);
                value = dialog.findViewById(R.id.rateValue);
                TextView title = dialog.findViewById(R.id.rateHeader);

                title.setText(attractionName);

                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                        value.setText(String.valueOf(v));
                    }
                });

                Button submitRateBtn = (Button) dialog.findViewById(R.id.submitRateBtn);
                Button cancelBtn = (Button) dialog.findViewById(R.id.cancelRateBtn);
                submitRateBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                EditText txtReview = dialog.findViewById(R.id.reviewED);
                                String review = txtReview.getText().toString();
                                String attractionURL = attraction.getApiURL();
                                int ratings = (int) ratingBar.getRating();
                                rateReviewManager.RateAndReview(db,ratings,review,attractionURL,username);
                                attraction.setOverallRating(rateReviewManager.calculateOverallRating(db,attractionURL,ratings));
                                Log.d("rating",String.valueOf(attraction.getOverallRating()));
                                rating.setText(String.valueOf(attraction.getOverallRating()));
                                dialog.dismiss();
                            }
                        }).start();
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

}
