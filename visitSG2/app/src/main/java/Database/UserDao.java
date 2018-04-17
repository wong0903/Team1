package Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import entity.User;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by wong0903 on 10/4/2018.
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users where loginID = :loginID")
    User findByID(String loginID);

    @Insert(onConflict = IGNORE)
    void insertUser(User user);

    @Delete
    void delete(User user);
}
