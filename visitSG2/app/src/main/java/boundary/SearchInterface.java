package boundary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.wong0903.visitsg.R;

import org.json.JSONException;

import control.SearchManager;

/**
 * Created by wong0903 on 20/3/2018.
 * This class allows user to input the attraction name and
 * display the relevant results.
 *
 */

public class SearchInterface extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstancedState){
        super.onCreate(savedInstancedState);
        setContentView(R.layout.activity_search);

        textView = findViewById(R.id.responseView);
        Intent intent = getIntent();
        String attraction = intent.getStringExtra("attraction");
        try {
            SearchManager.search(attraction);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
