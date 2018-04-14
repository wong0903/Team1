package boundary;

/**
 * Created by nigelleong on 14/4/18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.wong0903.visitsg.R;

import org.json.JSONException;

import java.util.ArrayList;

import control.SearchManager;


public class SearchFragment extends Fragment implements View.OnClickListener {

    EditText inputText;
    String attraction;
    Button btn_search;

    public SearchFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_search, container, false);
        inputText = (EditText) view.findViewById(R.id.inputText);

        btn_search = (Button) view.findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                    try {
                        SearchManager searchManager = new SearchManager();
                        attraction = inputText.getText().toString();
                        if(!attraction.isEmpty()) {
                            ArrayList<String> matchedURLList = searchManager.search(attraction);
                            Bundle information = new Bundle();
                            information.putStringArrayList("matchedURLList", matchedURLList);
                            Intent intent = new Intent(getActivity(), ListViewInterface.class);
                            intent.putExtras(information);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    }
                }).start();
                break;
            default:
                break;
        }

    }

}