package entity;

/**
 * Created by wong0903 on 20/3/2018.
 * This class implements the Attraction entity with
 * the attributes attractionID, name, address, description, webURL,
 * operatingHours, weatherType and overallRating. This class can get the direction
 * to the attraction.
 */

public class Attraction {
    private int attractionID;
    private String name;
    private String address;
    private String description;
    private String webURL;
    private int operatingHours;
    private String weatherType;
    private float overallRating;

    public Attraction(int attractionID, String name, String address,String description, String webURL,
                      int operatingHours,float overallRating){
        this.attractionID = attractionID;
        this.name = name;
        this.address = address;
        this.description = description;
        this.webURL = webURL;
        this.operatingHours = operatingHours;
        this.overallRating = overallRating;
    }

    public void getDirection(String address) {
        //Display the route to the attraction by calling GoogleMap API
    }

}
