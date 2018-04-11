package boundary;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.wong0903.visitsg.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import helper.SessionManager;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainInterface extends AppCompatActivity implements View.OnClickListener {

    EditText inputText;
    TextView responseView;
    ProgressBar progressBar;
    Button queryButton1;
    Button queryButton2;
    Button queryButton3;
    Button queryButton4;
    private SessionManager session;
    //tatic final String API_URL = "http://www.visitsingapore.com/ysapi-services/RequestAPI?format=details&locale=en&pageid=84";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseView = findViewById(R.id.responseView);
        inputText = findViewById(R.id.inputText);
        progressBar = findViewById(R.id.progressBar);

        queryButton1 = findViewById(R.id.queryButton1);
        queryButton2 = findViewById(R.id.queryButton2);
        queryButton3 = findViewById(R.id.queryButton3);
        queryButton4 = findViewById(R.id.queryButton4);

        queryButton1.setOnClickListener(this);
        queryButton2.setOnClickListener(this);
        queryButton3.setOnClickListener(this);
        queryButton4.setOnClickListener(this);

       // session = new SessionManager(getApplicationContext());
//
//        if(!session.isLoggedIn()){
//            //log out
//        }

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.queryButton1:
                Intent intent1 = new Intent(this, SearchInterface.class);
                intent1.putExtra("attraction", inputText.getText().toString());
                startActivity(intent1);
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
                Intent intent4 = new Intent(MainInterface.this, LoginInterface.class);
                startActivity(intent4);
                break;
            default:
                break;
        }

    }


//    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {
//
//        private Exception exception;
//
//        protected void onPreExecute() {
//            progressBar.setVisibility(View.VISIBLE);
//            responseView.setText("");
//        }
//
//        protected String doInBackground(Void... urls) {
//            String attraction = inputText.getText().toString();
//            // Do some validation here
//            try {
//                URL url = new URL(API_URL );
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestProperty("Content-Type","application/json");
//                urlConnection.setRequestProperty("email", "lleong009@e.ntu.edu.sg");
//                urlConnection.setRequestProperty("token", API_KEY);
//                try {
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//                    StringBuilder stringBuilder = new StringBuilder();
//                    String line;
//                    while ((line = bufferedReader.readLine()) != null) {
//                        stringBuilder.append(line);
//                    }
//                    bufferedReader.close();
//                    return stringBuilder.toString();
//                }
//                finally{
//                    urlConnection.disconnect();
//                }
//            }
//            catch(Exception e) {
//                Log.e("ERROR", e.getMessage(), e);
//                return null;
//            }
//        }
//
//        protected void onPostExecute(String response) {
//            if(response == null) {
//                response = "THERE WAS AN ERROR";
//            }
//            progressBar.setVisibility(View.GONE);
//            Log.i("INFO", response);
//            // TODO: check this.exception
//            // TODO: do something with the feed
//            try {
//
////                JSONObject json = new JSONObject(response);
////                responseView.setText(json.toString(1))
//                responseView.setText(retrieveBasicInformation(response));
////                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
////                String requestID = object.getString("requestId");
////                int likelihood = object.getInt("likelihood");
////                JSONArray photos = object.getJSONArray("photos");
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//        private String retrieveBasicInformation(String jsonString)
//                throws JSONException {
//                final String requestID = "id";
//                final String TITLE = "title";
//
//                JSONObject json = new JSONObject(jsonString);
//                String id = json.getString(requestID);
//                String attraction = json.getString(TITLE);
//
//                String results = attraction + "-----" + id;
//                return results;
//        }
//    }
}
