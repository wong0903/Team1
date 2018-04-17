package control;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.icu.lang.UCharacter.toLowerCase;

/**
 * Created by wong0903 on 20/3/2018.
 * This class will search the attractions that
 * matches the input text.
 */


public class SearchManager {
    private ArrayList<String> urlList;
    private List<Pair<String, String>> attractionList ;

    public ArrayList<String> search(String attraction) throws JSONException {
        urlList = new ArrayList<>();
        attractionList = retrieveAllAttractions();
        for(int i=0; i < attractionList.size(); i++) {
            if(attractionList.get(i).second.toLowerCase().trim().replace(" ","").
                    contains(attraction.toLowerCase().trim().replace(" ",""))) {
                urlList.add(attractionList.get(i).first);
            }
        }
        return urlList;
    }

    public List<Pair<String, String>> retrieveAllAttractions() {
        try {
            attractionList = new RetrieveFeedTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return attractionList;
    }

     class RetrieveFeedTask extends AsyncTask<Void, Void, List<Pair<String, String>>> {

        protected  List<Pair<String, String>> doInBackground(Void... urls) {
            // Do some validation here
            try {
                URL url = new URL("http://www.visitsingapore.com/ysapi-services/RequestAPI?format=listing&locale=en&pageid=2");
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
                    JSONArray contents = json.getJSONArray("details");
                    int size = contents.length();
                    int count = 0;
                    attractionList = new ArrayList<>();
                    while (size != 0) {
                        attractionList.add(new Pair<>(contents.getJSONObject(count).getString("url"),
                                contents.getJSONObject(count).getString("ptitle")));
                        size--;
                        count++;
                    }
                    return attractionList;
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
//    public List<Attraction> retrieveAllAttraction(){
//        //call to the visitSingapore API and return the list of attractions
//    }

