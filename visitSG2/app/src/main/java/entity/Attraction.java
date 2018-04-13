package entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by wong0903 on 20/3/2018.
 * This class implements the Attraction entity with
 * the attributes attractionID, name, address, description, webURL,
 * operatingHours, weatherType and overallRating. This class can get the direction
 * to the attraction.
 */

@Entity(tableName = "attractions")
public class Attraction implements Parcelable{
    @PrimaryKey @NonNull
    private String webURL;
    private String apiURL;
    private int id;
    private String name;
    private String address;
    private String description;
    private String thumbnailUrl;
    private String operatingHours;
    private String weatherType;
    private double overallRating;

    public Attraction(){
        this.setId(0);
        this.setName("");
        this.setAddress("");
        this.setDescription("");
        this.setWebURL("");
        this.setOverallRating(0.0);
        this.setWeatherType("");
    }

    public Attraction(int attractionID, String name, String address,String description, String webURL,
                      String operatingHours,double overallRating, String category, String thumbnailUrl){
        this.id = attractionID;
        this.name = name;
        this.address = address;
        this.description = description;
        this.webURL = webURL;
        this.operatingHours = operatingHours;
        this.overallRating = overallRating;
        this.thumbnailUrl = thumbnailUrl;

    }

    protected Attraction(Parcel in) {
        name = in.readString();
        address = in.readString();
        operatingHours = in.readString();
        thumbnailUrl = in.readString();
        description = in.readString();
        apiURL = in.readString();
    }

    public static final Creator<Attraction> CREATOR = new Creator<Attraction>() {
        @Override
        public Attraction createFromParcel(Parcel in) {
            return new Attraction(in);
        }

        @Override
        public Attraction[] newArray(int size) {
            return new Attraction[size];
        }
    };

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

    public int getId() {
        return id;
    }

    public void setId(int attractionID) {
        this.id = attractionID;
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

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(operatingHours);
        parcel.writeString(thumbnailUrl);
        parcel.writeString(description);
        parcel.writeString(apiURL);

    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getApiURL() {
        return apiURL;
    }

    public void setApiURL(String apiURL) {
        this.apiURL = apiURL;
    }
}
