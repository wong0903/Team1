package Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import entity.Category;

/**
 * Created by wong0903 on 13/4/2018.
 */

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM categories")
    List<Category> getAll();

    @Query("SELECT DISTINCT type FROM categories")
    List<String> getCategoryType();

    @Query("SELECT attractionURL FROM categories WHERE type = :type")
    List<String> findAttractionByCategory(String type);

}
