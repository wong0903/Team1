package Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import entity.RatingAndReview;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by wong0903 on 14/4/2018.
 * Contains the methods used for accessing the rating and review database.
 */

@Dao
public interface RatingAndReviewDao {

    @Query("SELECT * FROM ratings WHERE attractionURL = :attractionURL")
    List<RatingAndReview> getAttractionRatingAndReview(String attractionURL);

    @Query("SELECT COUNT(rating) FROM ratings WHERE attractionURL = :attractionURL")
    int getNumberOfRaters(String attractionURL);

    @Query("SELECT SUM(rating) FROM ratings WHERE attractionURL = :attractionURL")
    int getTotalRatings(String attractionURL);

    @Insert(onConflict = IGNORE)
    void insertRatingAndReview(RatingAndReview ratingAndReview);

    @Delete
    void delete(RatingAndReview ratingAndReview);

}
