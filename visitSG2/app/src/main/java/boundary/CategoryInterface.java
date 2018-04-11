package boundary;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.wong0903.visitsg.R;

/**
 * Created by wong0903 on 20/3/2018.
 * This class display the categories type and attractions under a category.
 */

public class CategoryInterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstancedState){
        super.onCreate(savedInstancedState);
        setContentView(R.layout.activity_categories);
    }

    public void displayCategories(){}

    public void displayAttraction(){}
}
