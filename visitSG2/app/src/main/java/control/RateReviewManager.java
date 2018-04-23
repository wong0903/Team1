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
 * This class contains three key methods which is RateAndReview, calculateOverallRating and verifyRating.
 * The first method will update the user rating and review to the database and update the number of
 * raters.
 * The second method will retrieve the overall rating that is previously calculated and stored in
 * the database and recalculate it with the current user input to obtain the new overall rating.
 * If there is no record in the database, that means it nobody has rated this attraction so it will
 * just take the user rating as the overall rating and update into the database.
 * The third method is to check if the user input rating is not 0. If yes, then ask the user to input
 * again.
 */

public class RateReviewManager {
    public void RateAndReview(AppDatabase db, int rating, String review, String attractionURL, String username){
        RatingAndReview ratingAndReview = new RatingAndReview(rating,review,attractionURL,username);
        db.ratingAndReviewDao().insertRatingAndReview(ratingAndReview);
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
            return (double) rating;
        }
    }

    public boolean verifyRating(Context c, int rating){
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
