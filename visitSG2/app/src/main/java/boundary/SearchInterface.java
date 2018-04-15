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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

import java.util.ArrayList;

import control.SearchManager;


public class SearchInterface extends Fragment implements View.OnClickListener, OnMapReadyCallback {

    EditText inputText;
    String attraction;
    Button btn_search;

    public SearchInterface() {
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

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user receives a prompt to install
     * Play services inside the SupportMapFragment. The API invokes this method after the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng singapore = new LatLng(1.338709, 103.819519);
        googleMap.addMarker(new MarkerOptions().position(singapore)
                .title("Marker in Singapore"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(singapore));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(11),3000,null);
    }
}