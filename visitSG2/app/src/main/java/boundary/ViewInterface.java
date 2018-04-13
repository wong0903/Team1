package boundary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.wong0903.visitsg.R;

import java.util.ArrayList;

import entity.Attraction;

/**
 * Created by wong0903 on 13/4/2018.
 */

public class ViewInterface extends AppCompatActivity {

    TextView name,address,operating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Bundle information = getIntent().getExtras();
        ArrayList<Attraction> attractionList = information.getParcelableArrayList("matchedAttractionList");

        name = findViewById(R.id.attractionName);
        address = findViewById(R.id.attractionAddress);
        operating = findViewById(R.id.operating);

        name.setText(attractionList.get(0).getName());
        address.setText(attractionList.get(0).getAddress());
        operating.setText(attractionList.get(0).getOperatingHours());

    }
}
