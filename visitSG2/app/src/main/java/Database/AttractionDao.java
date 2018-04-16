package Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

import entity.Attraction;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by wong0903 on 14/4/2018.
 */

@Dao
public interface AttractionDao {

    @Query("SELECT * FROM attraction")
    List<Attraction> getAttractionList();

    @Query("SELECT count FROM attraction WHERE apiURL = :attractionURL")
    int getCountbyAttractionURL(String attractionURL);

    @Query("SELECT apiURL FROM attraction ORDER BY overallRating DESC LIMIT 60")
    List<String> getSortedAttractionList();

    @Query("SELECT overallRating FROM attraction WHERE apiURL = :attractionURL")
    double getOverallRatingByAttractionURL(String attractionURL);

    @Query("UPDATE attraction SET count = count+1 WHERE apiURL = :attractionURL")
    void updateNumberOfRaters(String attractionURL);

    @Query("UPDATE attraction SET overallRating = :overallRating WHERE apiURL = :attractionURL")
    void updateOverallRating(String attractionURL, double overallRating );

    @Insert(onConflict = IGNORE)
    void insertAttraction(Attraction attraction);

    @Delete
    void delete(Attraction overallRating);
}
