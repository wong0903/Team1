package control;

import java.util.ArrayList;
import java.util.List;

import entity.Attraction;

/**
 * Created by wong0903 on 20/3/2018.
 * This class will retrieve a list of attraction from the local database
 * and return a list of attraction order by the ratings in descending order.
 */

public class SuggestionManager {
    private List<Attraction> suggestedList = new ArrayList<Attraction>();
    public List<Attraction> retrieveSortedRatingList(){
        //return a list of attraction with overall ratings and name from the local database.
        return suggestedList;
    }

    public List<Attraction> getSuggestions(){
        suggestedList = retrieveSortedRatingList();
        /*call to the visitSingapore API and
        return the basic information of the attraction in the sorted list
        */
    }

}
