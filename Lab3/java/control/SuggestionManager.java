package control;

import java.util.ArrayList;
import java.util.List;

import entity.Attraction;

/**
 * Created by wong0903 on 20/3/2018.
 * This class will return a list of attraction
 * order by the ratings in descending order.
 */

public class SuggestionManager {
    private List<Attraction> suggestedList = new ArrayList<Attraction>();
    public List<Attraction> suggest(){
        //return a sorted list of attraction
        return suggestedList;
    }

}
