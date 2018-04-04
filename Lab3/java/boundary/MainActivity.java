package boundary;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.wong0903.visitsg.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {

    EditText inputText;
    TextView responseView;
    ProgressBar progressBar;
    static final String API_KEY = "407ab3a352953114d6f09f653c1386a06f5d78a680ec02d643984782a8305bfd4231274fa545d2501fc9f65359eb61b1a64acc1a47f2c87f87147de47c07ab1d";
    static final String API_URL = "http://www.visitsingapore.com/ysapi-services/RequestAPI?format=details&locale=en&pageid=84";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseView = (TextView) findViewById(R.id.responseView);
        inputText = (EditText) findViewById(R.id.inputText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Button queryButton = (Button) findViewById(R.id.queryButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RetrieveFeedTask().execute();
            }
        });
    }


    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            responseView.setText("");
        }

        protected String doInBackground(Void... urls) {
            String attraction = inputText.getText().toString();
            // Do some validation here
            try {
                URL url = new URL(API_URL );
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Content-Type","application/json");
                urlConnection.setRequestProperty("email", "lleong009@e.ntu.edu.sg");
                urlConnection.setRequestProperty("token", API_KEY);
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            // TODO: check this.exception
            // TODO: do something with the feed
            try {

//                JSONObject json = new JSONObject(response);
//                responseView.setText(json.toString(1));
                    responseView.setText(retrieveBasicInformation(response));
//                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
//                String requestID = object.getString("requestId");
//                int likelihood = object.getInt("likelihood");
//                JSONArray photos = object.getJSONArray("photos");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private String retrieveBasicInformation(String jsonString)
                throws JSONException {
                final String requestID = "id";
                final String TITLE = "title";

                JSONObject json = new JSONObject(jsonString);
                String id = json.getString(requestID);
                String attraction = json.getString(TITLE);

                String results = attraction + "-----" + id;
                return results;
        }
    }
}
