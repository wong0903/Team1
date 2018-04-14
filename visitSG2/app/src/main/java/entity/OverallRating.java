package entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by wong0903 on 14/4/2018.
 */

@Entity(tableName = "OverallRating")
public class OverallRating {
    @PrimaryKey @NonNull
    private String attractionURL;
    @ColumnInfo(name = "overallRating")
    private double overallRating;

    public OverallRating(){
        this.setAttractionURL("");
        this.setOverallRating(0.0);
    }
    public OverallRating(String attractionURL, double overallRating){
        this.attractionURL = attractionURL;
        this.overallRating = overallRating;
    }

    @NonNull
    public String getAttractionURL() {
        return attractionURL;
    }

    public void setAttractionURL(@NonNull String attractionURL) {
        this.attractionURL = attractionURL;
    }

    public double getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(double overallRating) {
        this.overallRating = overallRating;
    }
}
