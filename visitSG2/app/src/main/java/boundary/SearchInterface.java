package boundary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import helper.CustomListAdapter;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SearchInterface extends AppCompatActivity implements View.OnClickListener {

    EditText inputText;
    TextView responseView;
    ProgressBar progressBar;
    Button btn_search;
    Button btn_categories;
    Button btn_suggestions;
    Button btn_login;
    String attraction;
    List<Attraction> matchedAttractionList;
//    private ListView listView;
//    private CustomListAdapter adapter;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        listView = (ListView) findViewById(R.id.list);
//        adapter = new CustomListAdapter(this, matchedAttractionList);
//        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

//        responseView = findViewById(R.id.responseView);
//        inputText = (EditText) findViewById(R.id.inputText);
//        progressBar = findViewById(R.id.progressBar);

        // changing action bar color
        getActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#1b1b1b")));

        btn_search = findViewById(R.id.btn_search);
        btn_categories = findViewById(R.id.btn_categories);
        btn_suggestions = findViewById(R.id.btn_suggestions);
        btn_login = findViewById(R.id.btn_login);

        btn_search.setOnClickListener(this);
        btn_categories.setOnClickListener(this);
        btn_suggestions.setOnClickListener(this);
        btn_login.setOnClickListener(this);
//        session = new SessionManager(getApplicationContext());
//
//        if(!session.isLoggedIn()){
//            //log out
//        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
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
            case R.id.btn_categories:
                Intent intent2 = new Intent(this, CategoryInterface.class);
                startActivity(intent2);
                break;
            case R.id.btn_suggestions:
                Intent intent3 = new Intent(this, SuggestionInterface.class);
                startActivity(intent3);
                break;
            case R.id.btn_login:
                Intent intent4 = new Intent(SearchInterface.this, LoginInterface.class);
                startActivity(intent4);
                break;
            default:
                break;
        }

    }
}