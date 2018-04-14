package boundary;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ListViewInterface extends Fragment {
    List<String> matchedURLList = new ArrayList<>();
    List<String> basicInformationList = new ArrayList<>();
    List<Attraction> matchedAttractionList = new ArrayList<>();
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        Bundle information = getIntent().getExtras();
        matchedURLList = information.getStringArrayList("matchedURLList");

        AttractionManager attractionManager = new AttractionManager();
        for (String url : matchedURLList) {
            Attraction attraction = new Attraction();
            basicInformationList = attractionManager.retrieveBasicInformation(url);
            if (basicInformationList != null) {
                attraction.setName(basicInformationList.get(0));
                attraction.setAddress(basicInformationList.get(1));
                attraction.setOperatingHours(basicInformationList.get(2));
                attraction.setThumbnailUrl(basicInformationList.get(3));
                attraction.setWebURL(basicInformationList.get(4));
                attraction.setApiURL(basicInformationList.get(5));
                attraction.setOverallRating(0);
                matchedAttractionList.add(attraction);
            }
        }
        listView = findViewById(R.id.list);
        adapter = new CustomListAdapter(this, matchedAttractionList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                Attraction attraction = matchedAttractionList.get(position);
                ;
                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), AttractionInterface.class);
                // sending data to new activity
                i.putExtra("attraction", attraction);
                startActivity(i);
            }
        });

        return view
    }


    public ListView getListView() {
        return listView;
    }
}
