package boundary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wong0903.visitsg.R;

import control.AttractionManager;

/**
 * Created by wong0903 on 9/4/2018.
 */

public class AttractionInterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        String attractionURL = i.getStringExtra("attractionURL");

        AttractionManager attractionManager = new AttractionManager();
        attractionManager.retrieveDetailedInformation(attractionURL);

    }
}
