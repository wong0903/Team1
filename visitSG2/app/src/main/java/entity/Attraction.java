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
    private double overallRating;

    public Attraction(){
        this.setName("");
        this.setAttractionID(0);
        this.setAddress("");
        this.setDescription("");
        this.setWebURL("");
        this.setOverallRating(0.0);
        this.setWeatherType("");
    }

    public Attraction(int attractionID, String name, String address,String description, String webURL,
                      int operatingHours,double overallRating){
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebURL() {
        return webURL;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }

    public int getAttractionID() {
        return attractionID;
    }

    public void setAttractionID(int attractionID) {
        this.attractionID = attractionID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(int operatingHours) {
        this.operatingHours = operatingHours;
    }

    public double getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(double overallRating) {
        this.overallRating = overallRating;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }
}
