package boundary;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.wong0903.visitsg.R;

import java.util.ArrayList;
import java.util.List;

import Database.AppDatabase;
import control.AttractionManager;
import control.CategoryManager;
import entity.Attraction;

/**
 * Created by wong0903 on 20/3/2018.
 * This class display the categories type and attractions under a category.
 */

public class CategoryInterface extends AppCompatActivity implements View.OnClickListener {

    Button category1,category2,category3,category4,category5,category6,category7;
    private AppDatabase db;
    CategoryManager categoryManager = new CategoryManager();
    AttractionManager attractionManager = new AttractionManager();
    List<String> matchURLList = new ArrayList<>();
    List<String> basicInformationList = new ArrayList<>();
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
        category = ((Button) view).getText().toString();
        new Thread(new Runnable() {
        @Override
        public void run() {
            matchURLList = categoryManager.getAttractionsUnderCategory(category);
            for(String url: matchURLList) {
                Attraction attraction = new Attraction();
                basicInformationList = attractionManager.retrieveBasicInformation(url);
                if (basicInformationList == null) {
                    attraction.setName(basicInformationList.get(0));
                    attraction.setAddress(basicInformationList.get(1));
                    attraction.setOperatingHours(basicInformationList.get(2));
                }
            }
        }
        });
    }

}


