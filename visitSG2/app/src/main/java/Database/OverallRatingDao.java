package Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import entity.OverallRating;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by wong0903 on 14/4/2018.
 */

@Dao
public interface OverallRatingDao {

    @Query("SELECT * FROM OverallRating")
    List<OverallRating> getOverallRatingList();

    @Insert(onConflict = IGNORE)
    void insertOverallRating(OverallRating overallRating);

    @Delete
    void delete(OverallRating overallRating);
}
