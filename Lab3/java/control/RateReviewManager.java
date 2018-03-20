package control;

import java.util.ArrayList;
import java.util.List;

import entity.Rating;
import entity.Review;

/**
 * Created by wong0903 on 20/3/2018.
 * This class contains the all the ratings
 * and reviews of the attraction.
 */

public class RateReviewManager {
    private List<Rating> ratingList;
    private List<Review> reviewList;
    {
        reviewList = new ArrayList<Review>();
        ratingList = new ArrayList<Rating>();
    }


}
