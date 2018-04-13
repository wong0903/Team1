package boundary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wong0903.visitsg.R;


import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import control.AttractionManager;
import control.SearchManager;
import entity.Attraction;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SearchInterface extends AppCompatActivity implements View.OnClickListener {

    EditText inputText;
    TextView responseView;
    ProgressBar progressBar;
    Button queryButton1;
    Button queryButton2;
    Button queryButton3;
    Button queryButton4;
    String attraction;
    List<Attraction> matchedAttractionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseView = findViewById(R.id.responseView);
        inputText = (EditText) findViewById(R.id.inputText);
        progressBar = findViewById(R.id.progressBar);

        queryButton1 = findViewById(R.id.queryButton1);
        queryButton2 = findViewById(R.id.queryButton2);
        queryButton3 = findViewById(R.id.queryButton3);
        queryButton4 = findViewById(R.id.queryButton4);

        queryButton1.setOnClickListener(this);
        queryButton2.setOnClickListener(this);
        queryButton3.setOnClickListener(this);
        queryButton4.setOnClickListener(this);
//        session = new SessionManager(getApplicationContext());
//
//        if(!session.isLoggedIn()){
//            //log out
//        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.queryButton1:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SearchManager searchManager = new SearchManager();
                            AttractionManager attractionManager = new AttractionManager();
                            matchedAttractionList = new ArrayList<>();
                            attraction = inputText.getText().toString();
                            List<String> matchedURLList = searchManager.search(attraction);
                            if (!matchedURLList.isEmpty()) {
                                for (String url : matchedURLList) {
                                    Log.d("url", url);
                                    Attraction attraction = new Attraction();
                                    List<String> basicInformationList = attractionManager.retrieveBasicInformation(url);
                                    if (basicInformationList == null) {
                                        attraction.setName(basicInformationList.get(0));
                                        attraction.setAddress(basicInformationList.get(1));
                                        attraction.setOperatingHours(basicInformationList.get(2));
                                        matchedAttractionList.add(attraction);
                                    }
                                }
                            } else
                                Toast.makeText(getApplicationContext(), "No attractions found", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
                    break;
            case R.id.queryButton2:
                Intent intent2 = new Intent(this, CategoryInterface.class);
                startActivity(intent2);
                break;
            case R.id.queryButton3:
                Intent intent3 = new Intent(this, SuggestionInterface.class);
                startActivity(intent3);
                break;
            case R.id.queryButton4:
                Intent intent4 = new Intent(SearchInterface.this, LoginInterface.class);
                startActivity(intent4);
                break;
            default:
                break;
        }

    }
}