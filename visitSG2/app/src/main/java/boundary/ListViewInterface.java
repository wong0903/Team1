package boundary;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wong0903.visitsg.R;

import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.List;

import Database.AppDatabase;
import control.AttractionManager;
import control.RateReviewManager;
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
    private Toolbar toolbar;
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_list_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle information = getIntent().getExtras();
        matchedURLList = information.getStringArrayList("matchedURLList");

        db = AppDatabase.getAppDatabase(getApplicationContext());

        AttractionManager attractionManager = new AttractionManager();
        for (String url : matchedURLList) {
            Attraction attraction = new Attraction();
            basicInformationList = attractionManager.retrieveBasicInformation(db ,url);
            if (basicInformationList != null && basicInformationList.size() > 0) {
                attraction.setName(basicInformationList.get(0));
                attraction.setAddress(basicInformationList.get(1));
                attraction.setOperatingHours(basicInformationList.get(2));
                attraction.setThumbnailUrl(basicInformationList.get(3));
                attraction.setWebURL(basicInformationList.get(4));
                attraction.setApiURL(basicInformationList.get(5));
                attraction.setOverallRating(Double.parseDouble(basicInformationList.get(6)));
                matchedAttractionList.add(attraction);
                db.attractionDao().insertAttraction(attraction);
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
                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), AttractionInterface.class);
                // sending data to new activity
                Log.d("ratingwer",String.valueOf(attraction.getOverallRating()));
                i.putExtra("attraction", attraction);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }

    public ListView getListView() {
        return listView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
