package entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by wong0903 on 20/3/2018.
 * This class implements the User entity with
 * the attributes loginID, password, creationDate,
 * ratingAndReview and review.
 * User can rate and review the attractions.
 */
@Entity(tableName = "users")
public class User{
    @PrimaryKey @NonNull
    private String loginID;
    @ColumnInfo(name = "password")
    private String password;


    public User(String loginID, String password) {
        this.loginID = loginID;
        this.password = password;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
