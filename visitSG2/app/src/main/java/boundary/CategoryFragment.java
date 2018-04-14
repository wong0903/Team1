package boundary;

/**
 * Created by nigelleong on 14/4/18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.wong0903.visitsg.R;

import java.util.ArrayList;

import control.CategoryManager;


public class CategoryFragment extends Fragment implements View.OnClickListener {

    Button category1,category2,category3,category4,category5,category6,category7;
    CategoryManager categoryManager = new CategoryManager();
    String category;

    public CategoryFragment() {
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
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        category1 = view.findViewById(R.id.category1);
        category2 = view.findViewById(R.id.category2);
        category3 = view.findViewById(R.id.category3);
        category4 = view.findViewById(R.id.category4);
        category5 = view.findViewById(R.id.category5);
        category6 = view.findViewById(R.id.category6);
        category7 = view.findViewById(R.id.category7);

        category1.setOnClickListener(this);
        category2.setOnClickListener(this);
        category3.setOnClickListener(this);
        category4.setOnClickListener(this);
        category5.setOnClickListener(this);
        category6.setOnClickListener(this);
        category7.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view){
        category = ((Button) view).getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> matchedURLList;
                matchedURLList = categoryManager.getAttractionsUnderCategory(category);
                Fragment fragment = new Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
                Bundle information = new Bundle();
                information.putStringArrayList("matchedURLList", matchedURLList);
                fragment.setArguments(information);
//                Intent intent = new Intent(getActivity(), ListViewInterface.class);
//                intent.putExtras(information);
//                startActivity(intent);
            }
        }).start();
    }

}