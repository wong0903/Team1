package boundary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.wong0903.visitsg.R;

import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.List;

import control.AttractionManager;
import entity.Attraction;

/**
 * Created by wong0903 on 13/4/2018.
 */

public class ViewInterface extends AppCompatActivity {
    List<String> matchedURLList = new ArrayList<>();
    List<String> basicInformationList = new ArrayList<>();
    List<Attraction> matchedAttractionList = new ArrayList<>();
    TextView name,address,operating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Bundle information = getIntent().getExtras();
        matchedURLList = information.getStringArrayList("matchedURLList");

        name = findViewById(R.id.attractionName);
        address = findViewById(R.id.attractionAddress);
        operating = findViewById(R.id.operating);

        AttractionManager attractionManager = new AttractionManager();
        for(String url: matchedURLList) {
                Attraction attraction = new Attraction();
                basicInformationList = attractionManager.retrieveBasicInformation(url);
                if (basicInformationList != null) {
                    attraction.setName(basicInformationList.get(0));
                    attraction.setAddress(basicInformationList.get(1));
                    attraction.setOperatingHours(basicInformationList.get(2));
                    matchedAttractionList.add(attraction);
                }
            }

    }
}
