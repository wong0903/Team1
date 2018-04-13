package boundary;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wong0903.visitsg.R;

import java.util.ArrayList;
import java.util.List;

import entity.Attraction;
import helper.CustomListAdapter;

/**
 * Created by wong0903 on 13/4/2018.
 */

public class ViewInterface extends AppCompatActivity {

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
        ArrayList<Attraction> attractionList = information.getParcelableArrayList("matchedAttractionList");

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, attractionList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

//        name = findViewById(R.id.attractionName);
//        address = findViewById(R.id.attractionAddress);
//        operating = findViewById(R.id.operating);
//
//        name.setText(attractionList.get(0).getName());
//        address.setText(attractionList.get(0).getAddress());
//        operating.setText(attractionList.get(0).getOperatingHours());

    }
}