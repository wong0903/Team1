package entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import java.util.Date;

/**
 * Created by wong0903 on 20/3/2018.
 * This class implements the RatingAndReview entity with
 * attributes rating and creationDate.
 */

@Entity(tableName = "ratings")
public class RatingAndReview {
    private String attractionURL;
    @ColumnInfo(name = "user")
    private String username;
    private int rating = 0;
    private String review = "";
    private Date date;


    public RatingAndReview(){
        this.setRating(0);
        this.setAttractionURL("");
        this.setDate(null);
        this.setReview("");
        this.setUsername("");
    }

    public RatingAndReview(int rating, String attractionURL, Date date, String review, String username) {
        this.rating = rating;
        this.attractionURL = attractionURL;
        this.date = date;
        this.review = review;
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAttractionURL() {
        return attractionURL;
    }

    public void setAttractionURL(String attractionID) {
        this.attractionURL = attractionURL;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String loginID) {
        this.username = username;
    }
}
