package entity;

import android.app.Application;
import android.content.Context;

import com.example.wong0903.visitsg.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wong0903 on 16/4/2018.
 * This class contains the type of category and the image url for each category.
 */

public class Category extends Application{

    private String type;
    private String imageURL;

    public Category(String type, String imageURL) {
        this.type = type;
        this.imageURL = imageURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}
