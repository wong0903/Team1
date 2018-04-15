package Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import entity.LoggedInUser;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by wong0903 on 14/4/2018.
 */

@Dao
public interface LoggedInUserDao {

    @Query("SELECT * FROM loggedinuser")
    LoggedInUser getUser();

    @Query("SELECT * FROM loggedinuser where loginID = :loginID")
    LoggedInUser findByID(String loginID);

    @Insert(onConflict = IGNORE)
    void insertUser(LoggedInUser user);

    @Delete
    void delete(LoggedInUser user);
}

