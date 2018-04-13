package boundary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wong0903.visitsg.R;

import java.util.ArrayList;
import java.util.List;

import control.AttractionManager;
import entity.Attraction;

/**
 * Created by wong0903 on 9/4/2018.
 */

public class AttractionInterface extends AppCompatActivity {
    List<String> informationList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_view);

        Intent i = getIntent();
        Attraction attraction = i.getExtras().getParcelable("attraction");

        AttractionManager attractionManager = new AttractionManager();
        informationList = attractionManager.retrieveDetailedInformation(attraction.getApiURL());
        attraction.setDescription(informationList.get(informationList.size()));

    }
}
