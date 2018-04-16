package control;

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
}
