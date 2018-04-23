package boundary;

/**
 * Created by nigelleong on 14/4/18.
 * This class displays the seven categories
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wong0903.visitsg.R;

import java.util.ArrayList;
import java.util.List;

import entity.Category;
import helper.CategoryAdapter;


public class CategoryInterface extends Fragment {

    private List<Category> category;

    public CategoryInterface() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);

        initializeData();

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
        CategoryAdapter adapter = new CategoryAdapter(category);
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
    }

    public void initializeData(){
        category = new ArrayList<>();
        category.add(new Category(getString(R.string.category1),getString(R.string.image1)));
        category.add(new Category(getString(R.string.category2),getString(R.string.image2)));
        category.add(new Category(getString(R.string.category3),getString(R.string.image3)));
        category.add(new Category(getString(R.string.category4),getString(R.string.image4)));
        category.add(new Category(getString(R.string.category5),getString(R.string.image5)));
        category.add(new Category(getString(R.string.category6),getString(R.string.image6)));
        category.add(new Category(getString(R.string.category7),getString(R.string.image7)));
    }
}