package boundary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.wong0903.visitsg.R;

import java.util.ArrayList;
import java.util.List;

import Database.AppDatabase;
import control.AttractionManager;
import control.RateReviewManager;
import control.SuggestionManager;
import entity.Attraction;
import helper.CustomListAdapter;

/**
 * Created by nigelleong on 14/4/18.
 * This class will display the top 20 attractions sorted by overall ratings with basic information
 * It will call the retrieveSortedAttractionList in the Suggestion Manager to retrieve the list.
 */

public class SuggestionInterface extends Fragment implements View.OnClickListener {

    Button btn_suggested;
    AppDatabase db;

    public SuggestionInterface() {
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
        View view =  inflater.inflate(R.layout.fragment_suggestion, container, false);

        db = AppDatabase.getAppDatabase(getContext());
        btn_suggested = view.findViewById(R.id.top20);

        btn_suggested.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> returnList;
                ArrayList<String> suggestedList;
                returnList = SuggestionManager.retrieveSortedAttractionURLList(db);
                suggestedList = (ArrayList) returnList;
                Bundle information = new Bundle();
                information.putStringArrayList("matchedURLList", suggestedList);
                Intent intent = new Intent(getActivity(), ListViewInterface.class);
                intent.putExtras(information);
                startActivity(intent);
            }
        }).start();
    }
}