package entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

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
    @ColumnInfo(name = "email")
    private String email;


    public User(String loginID, String password, String email) {
        this.loginID = loginID;
        this.password = password;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
