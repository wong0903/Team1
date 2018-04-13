package entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by wong0903 on 13/4/2018.
 */

@Entity(tableName = "categories", foreignKeys = @ForeignKey(entity = Attraction.class,
                                    parentColumns = "webURL",
                                    childColumns = "attractionURL",
                                    onDelete = CASCADE))
public class Category {
    private String type;
    @PrimaryKey
    private String attractionURL;

    public String getAttractionURL() {
        return attractionURL;
    }

    public void setAttractionURL(String attractionURL) {
        this.attractionURL = attractionURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
