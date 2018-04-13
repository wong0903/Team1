package boundary;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.wong0903.visitsg.R;

import java.util.ArrayList;
import java.util.List;

import control.AttractionManager;
import control.CategoryManager;
import entity.Attraction;

/**
 * Created by wong0903 on 20/3/2018.
 * This class display the categories type and attractions under a category.
 */

public class CategoryInterface extends AppCompatActivity implements View.OnClickListener {

    Button category1,category2,category3,category4,category5,category6,category7;
    CategoryManager categoryManager = new CategoryManager();
    String category;
    @Override
    protected void onCreate(Bundle savedInstancedState) {
        super.onCreate(savedInstancedState);
        setContentView(R.layout.activity_categories);

        category1 = findViewById(R.id.category1);
        category2 = findViewById(R.id.category2);
        category3 = findViewById(R.id.category3);
        category4 = findViewById(R.id.category4);
        category5 = findViewById(R.id.category5);
        category6 = findViewById(R.id.category6);
        category7 = findViewById(R.id.category7);

        category1.setOnClickListener(this);
        category2.setOnClickListener(this);
        category3.setOnClickListener(this);
        category4.setOnClickListener(this);
        category5.setOnClickListener(this);
        category6.setOnClickListener(this);
        category7.setOnClickListener(this);

    }

    public void onClick(View view){
        ArrayList<String> matchedURLList = new ArrayList<>();
        category = ((Button) view).getText().toString();
        matchedURLList = categoryManager.getAttractionsUnderCategory(category);
//            int index = 0;
//            for(String url: matchURLList) {
//                Attraction attraction = new Attraction();
//                basicInformationList = attractionManager.retrieveBasicInformation(url);
//                if (basicInformationList != null) {
//                    attraction.setName(basicInformationList.get(0));
//                    attraction.setAddress(basicInformationList.get(1));
//                    attraction.setOperatingHours(basicInformationList.get(2));
//                    matchedAttractionList.add(attraction);
//                }
//            }
        Bundle information = new Bundle();
        information.putStringArrayList("matchedURLList", matchedURLList);
        Intent intent = new Intent(CategoryInterface.this, ViewInterface.class);
        intent.putExtras(information);
        startActivity(intent);
        }

    }


