package Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import entity.Attraction;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by wong0903 on 12/4/2018.
 */

@Dao
public interface AttractionDao {
    @Query("SELECT * FROM attractions")
    List<Attraction> getAll();


//    @Query("SELECT DISTINCT category FROM attractions")
//    List<String> getCategory();
//
//    @Query("SELECT * FROM attractions WHERE category = :category")
//    List<Attraction> findByCategory(String category);

    @Query("SELECT * FROM attractions ORDER BY overallRating DESC")
    List<Attraction> getSortedOverallRating();

    @Insert(onConflict = IGNORE)
    void insertAttraction(Attraction attraction);

    @Delete
    void delete(Attraction attraction);
}

