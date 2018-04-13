package boundary;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wong0903.visitsg.R;

import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.List;

import control.AttractionManager;
import entity.Attraction;
import helper.CustomListAdapter;

/**
 * Created by wong0903 on 13/4/2018.
 */

public class ViewInterface extends AppCompatActivity {
    List<String> matchedURLList = new ArrayList<>();
    List<String> basicInformationList = new ArrayList<>();
    List<Attraction> matchedAttractionList = new ArrayList<>();
    TextView name,address,operating;

    private List<Attraction> attractionList = new ArrayList<Attraction>();
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_list_view);

        // changing action bar color
//        getActionBar().setBackgroundDrawable(
//                new ColorDrawable(Color.parseColor("#1b1b1b")));

        Bundle information = getIntent().getExtras();
        matchedURLList = information.getStringArrayList("matchedURLList");

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, attractionList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

<<<<<<< HEAD
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
=======
//        name = findViewById(R.id.attractionName);
//        address = findViewById(R.id.attractionAddress);
//        operating = findViewById(R.id.operating);
//
//        name.setText(attractionList.get(0).getName());
//        address.setText(attractionList.get(0).getAddress());
//        operating.setText(attractionList.get(0).getOperatingHours());
>>>>>>> 4641826d384b5fd5fb570d31c8ccf999d2ea2246

    }
}