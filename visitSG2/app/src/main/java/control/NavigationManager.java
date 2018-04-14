package control;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wong0903 on 14/4/2018.
 */

public class NavigationManager extends AppCompatActivity {

    private String longitude;
    private String latitude;

    public void getNavigation(String longitude, String latitude){
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+latitude+","+longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}

