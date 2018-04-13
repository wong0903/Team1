package control;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by wong0903 on 11/4/2018.
 */

public class AttractionManager {
    String webURL = "";
    String name, address, operatingHours;
    List<String> informationList = new ArrayList<>();

    public List<String> retrieveBasicInformation(String matchedURL){
        webURL = matchedURL;
        try {
            return new RetrieveFeedTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

     class RetrieveFeedTask extends AsyncTask<Void, Void, List<String>> {

        protected List<String> doInBackground(Void... urls) {
            // Do some validation here
            try {
                URL url = new URL(webURL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("email", "lleong009@e.ntu.edu.sg");
                urlConnection.setRequestProperty("token", "407ab3a352953114d6f09f653c1386a06f5d78"
                        + "a680ec02d643984782a8305bfd4231274fa545d2501fc9f65359eb61b1a64acc1a47f2c87f87147de47c07ab1d");
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    bufferedReader.close();

                    JSONObject json = new JSONObject(stringBuilder.toString());
                    if(!json.has("error")) {
                        name = json.getString("title");
                        informationList.add(name);
                        address = json.getString("address");
                        informationList.add(address);
                        operatingHours = json.getString("opening-hours");
                        informationList.add(operatingHours);
                    }
                    return informationList;
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
            Log.i("INFO", response);
            // TODO: check this.exception
            // TODO: do something with the feed
        }
    }
}

