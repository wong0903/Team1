package control;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Database.AppDatabase;


/**
 * Created by wong0903 on 11/4/2018.
 * This class contains the retrieveBasicInformation, retrieveDetailedInformation and getNavigation
 * method.
 *
 * This first method will call to visitSingapore API to retrieve the basic information.
 *
 * The second method will also call to visitSingapore API to retrieve the detailed information.
 *
 * The getNavigation method which will call to the google map App on the device
 * to get the direction to the attraction by passing the attraction's latitude and longitude as
 * parameters.
 *
 */

public class AttractionManager {
    private String apiURL= "";
    private List<String> informationList = new ArrayList<>();

    public List<String> retrieveBasicInformation(AppDatabase db, String matchedURL){
        List<String> basicInformationList = new ArrayList<>();
        apiURL = matchedURL;
        try {
            informationList = new RetrieveFeedTask().execute().get();
            if(informationList.size() != 0) {
                for (int i = 0; i < 6; i++) {
                    basicInformationList.add(informationList.get(i));
                }
            }
            basicInformationList.add(Double.toString(db.attractionDao().getOverallRatingByAttractionURL(matchedURL)));
            basicInformationList.add(String.valueOf(db.attractionDao().getCountbyAttractionURL(matchedURL)));
            return basicInformationList;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> retrieveDetailedInformation(AppDatabase db, String matchedURL){
        List<String> detailedInformationList = new ArrayList<>();
        apiURL = matchedURL;
        try {
            informationList = new RetrieveFeedTask().execute().get();
            if(informationList.size() != 0) {
                detailedInformationList.addAll(informationList);
            }
            detailedInformationList.add(Double.toString(db.attractionDao().getOverallRatingByAttractionURL(matchedURL)));
            detailedInformationList.add(String.valueOf(db.attractionDao().getCountbyAttractionURL(matchedURL)));
            return detailedInformationList;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getNavigation(String latitude, String longitude, Context c){
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+latitude+","+longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        c.startActivity(mapIntent);
    }

     class RetrieveFeedTask extends AsyncTask<Void, Void, List<String>> {

        protected List<String> doInBackground(Void... urls) {
            // Do some validation here
            try {
                URL url = new URL(apiURL);
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
                    informationList = new ArrayList<>();
                    if(!json.has("error")) {
                        String name = json.getString("title");
                        informationList.add(name);
                        String address = json.getString("address");
                        informationList.add(address);

                        String operatingHours = json.getString("opening-hours");
                        informationList.add(operatingHours);

                        JSONArray images = json.getJSONArray("images");
                        if(images != null && images.length() > 0) {
                            String image = images.getJSONObject(0).getString("url");
                            informationList.add(image);
                        }else
                            informationList.add("");

                        String webURL = json.getString("url");
                        informationList.add(webURL);

                        informationList.add(apiURL);

                        String content = json.getString("shortContent");
                        informationList.add(content);

                        String latitude = json.getString("latitude");
                        informationList.add(latitude);

                        String longitude = json.getString("longitude");
                        informationList.add(longitude);
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

