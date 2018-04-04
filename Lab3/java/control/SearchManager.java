package control;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import entity.Attraction;

/**
 * Created by wong0903 on 20/3/2018.
 * This class will search the attractions that
 * matches the input text.
 */

public class SearchManager {
    int index = 0;
    String information;
    private List<Attraction> attractionList = new ArrayList<Attraction>();
    public List<Attraction> search(String inputText) throws JSONException {
        attractionList = retrieveAllAttraction();
        while (attractionList.size() != 0) {
            if (attractionList.get(index).getName().toLowerCase().contains(inputText.toLowerCase())) {
                information = RetrieveBasicInformation.retrieve(attractionList.get(index).getWebURL());
                JSONObject object = new JSONObject(information);
                Attraction attraction = new Attraction();
                attraction.setName(object.getString("title"));
                attraction.setAddress();
        /*call retrieveAllAttraction() to return the attraction list
        and match the input text with the attraction list.If it is found
        call retrieve() in the RetrieveBasicInformation class else keep looping until
        the attraction list ends.
         */

            }
        }
    }

    public List<Attraction> retrieveAllAttraction(){
        //call to the visitSingapore API and return the list of attractions
    }
}
