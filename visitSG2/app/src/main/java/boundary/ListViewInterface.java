package boundary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class ListViewInterface extends AppCompatActivity {
    List<String> matchedURLList = new ArrayList<>();
    List<String> basicInformationList = new ArrayList<>();
    List<Attraction> matchedAttractionList = new ArrayList<>();
    private ListView listView;
    private CustomListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_list_view);

        Bundle information = getIntent().getExtras();
        matchedURLList = information.getStringArrayList("matchedURLList");

        AttractionManager attractionManager = new AttractionManager();
        for(String url: matchedURLList) {
            Attraction attraction = new Attraction();
            basicInformationList = attractionManager.retrieveBasicInformation(url);
            if (basicInformationList != null) {
                attraction.setName(basicInformationList.get(0));
                attraction.setAddress(basicInformationList.get(1));
                attraction.setOperatingHours(basicInformationList.get(2));
                attraction.setThumbnailUrl(basicInformationList.get(3));
                attraction.setWebURL(basicInformationList.get(4));
                attraction.setApiURL(basicInformationList.get(5));
                matchedAttractionList.add(attraction);
            }
        }
        listView = findViewById(R.id.list);
        adapter = new CustomListAdapter(this, matchedAttractionList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

<<<<<<< HEAD:visitSG2/app/src/main/java/boundary/ListViewInterface.java
        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                String attractionURL = matchedAttractionList.get(position).getApiURL();
                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), AttractionInterface.class);
                // sending data to new activity
                i.putExtra("attractionURL", attractionURL);
                startActivity(i);
            }
        });

    }

    public ListView getListView() {
        return listView;
=======


>>>>>>> d15fd61e6f810f377dfebd4ab93704b312525f59:visitSG2/app/src/main/java/boundary/ViewInterface.java
    }
}
