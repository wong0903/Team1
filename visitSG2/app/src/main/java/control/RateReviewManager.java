package control;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.util.List;

import Database.AppDatabase;
import entity.RatingAndReview;

/**
 * Created by wong0903 on 20/3/2018.
 * This class contains the all the ratings
 * and reviews and he overall ratings of the attraction.
 */

public class RateReviewManager {
    private List<RatingAndReview> ratingAndReviewList;
    public void RateAndReview(AppDatabase db, int rating, String review, String attractionURL, String username){
        RatingAndReview ratingAndReview = new RatingAndReview(rating,review,attractionURL,username);
        db.ratingAndReviewDao().insertRatingAndReview(ratingAndReview);
        ratingAndReviewList = db.ratingAndReviewDao().getAttractionRatingAndReview(attractionURL);
        db.attractionDao().updateNumberOfRaters(attractionURL);
    }

    public double calculateOverallRating(AppDatabase db, String attractionURL, int rating) {
        int count = db.attractionDao().getCountbyAttractionURL(attractionURL);
        if(count > 1) {
            double oldOverallRating = db.attractionDao().getOverallRatingByAttractionURL(attractionURL);
            double oldTotalRatings = oldOverallRating * (count - 1);
            double newOverallRating = (rating + oldTotalRatings)/count;
            db.attractionDao().updateOverallRating(attractionURL, newOverallRating);
            return newOverallRating;
        }
        else {
            db.attractionDao().updateOverallRating(attractionURL, rating);
            return rating;
        }
    }

    public double retrieveAttractionOverallRating(AppDatabase db, String attractionURL){
        double overallRating = db.attractionDao().getOverallRatingByAttractionURL(attractionURL);
        return overallRating;
    }

    public boolean verfiyRating(Context c, int rating){
        if(rating == 0){
            toast(c,"Rating must be 1 to 5");
            return false;
        }else
            return true;
    }

    public static void toast(final Context context, final String text) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
