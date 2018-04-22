package control;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by wong0903 on 14/4/2018.
 * This class contains the getNavigation method which will call to the google map App on the device
 * to get the direction to the attraction by passing the attraction's latitude and longitude as
 * parameters.
 */

public class NavigationManager extends AppCompatActivity {

    public void getNavigation(String latitude, String longitude, Context c){
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+latitude+","+longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        c.startActivity(mapIntent);
    }
}

