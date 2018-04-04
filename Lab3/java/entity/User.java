package entity;

import java.util.Date;


/**
 * Created by wong0903 on 20/3/2018.
 * This class implements the User entity with
 * the attributes loginID, password, creationDate,
 * ratingAndReview and review.
 * User can rate and review the attractions.
 */

public class User{
    private String loginID;
    private String password;
    private Date creationDate;


    public User(){
        loginID = "";
        password = "";
    }

    public User(String loginID, String password){
        this.loginID = loginID;
        this.password = password;
    }

    public void setRatingAndReview(String attractionID){

    }

    public void setReview(String attractionID){

    }

}
