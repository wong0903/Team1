package control;

import java.util.ArrayList;
import java.util.List;

import entity.Attraction;
import entity.Category;

/**
 * Created by wong0903 on 20/3/2018.
 */

public class CategoryManager {
    private List<Category> categoryList = new ArrayList<Category>();

    public List<Category> getCategories() {
        return categoryList;
    }

    public List<Category> retrieveCategories(){
        //return a list of Categories from the local database
    }

    public List<Attraction> getAttractionsUnderCategory(String category){
        /*call to the visitSingapore API and return
        the basic information of the attraction in the category list
         */
    }

    public List<Attraction> retrieveAttractionsUnderCategory(String category){
        //return a list of attractions under the category from the visitSingapore API
    }
}
