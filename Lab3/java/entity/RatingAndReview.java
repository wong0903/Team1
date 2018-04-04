package entity;

import java.util.Date;

/**
 * Created by wong0903 on 20/3/2018.
 * This class implements the RatingAndReview entity with
 * attributes rating and creationDate.
 */

public class RatingAndReview {
    private int rating;
    private String attractionID;
    private Date date;
    private String review;
    private String loginID;

    public RatingAndReview(){
        this.setRating(0);
        this.setAttractionID("");
        this.setDate("");
        this.setReview("");
        this.setLoginID("");
    }

    public RatingAndReview(int rating, String attractionID, Date date, String review, String loginID) {
        this.rating = rating;
        this.attractionID = attractionID;
        this.date = date;
        this.review = review;
        this.loginID = loginID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAttractionID() {
        return attractionID;
    }

    public void setAttractionID(String attractionID) {
        this.attractionID = attractionID;
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

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }
}
