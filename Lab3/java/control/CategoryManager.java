package control;

import java.util.ArrayList;
import java.util.List;

import entity.Category;

/**
 * Created by wong0903 on 20/3/2018.
 */

public class CategoryManager {
    private List<Category> categoryList = new ArrayList<Category>();

    public List<Category> request() {
        //return a list of Categories
        return categoryList;
    }
}
