package control;

import java.util.ArrayList;
import java.util.List;

import Database.AppDatabase;
import entity.Attraction;

/**
 * Created by wong0903 on 20/3/2018.
 * This class will retrieve a list of sorted attraction by the overall rating from the database
 * and return the list to the caller.
 */

public class SuggestionManager {
    private static List<String> suggestedList = new ArrayList<String>();
    public static List<String> retrieveSortedAttractionURLList(AppDatabase db){
        //return a list of attraction with overall ratings and name from the local database.
        suggestedList = db.attractionDao().getSortedAttractionList();
        return suggestedList;
    }


}
