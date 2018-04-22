package helper;

/**
 * Created by nigelleong on 13/4/18.
 * It's an interface between the attraction basic information data and ListView Interface layout
 */

import com.example.wong0903.visitsg.R;
import control.AppController;
import entity.Attraction;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Attraction> attractionItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<Attraction> attractionItems) {
        this.activity = activity;
        this.attractionItems = attractionItems;
    }


    @Override
    public int getCount() {
        return attractionItems.size();
    }

    @Override
    public Object getItem(int location) {
        return attractionItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView address = (TextView) convertView.findViewById(R.id.address);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        TextView operationHours = (TextView) convertView.findViewById(R.id.operationHours);
        TextView raters = convertView.findViewById(R.id.count);

        // getting movie data for the row
        Attraction a = attractionItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(a.getThumbnailUrl(), imageLoader);

        // title
        name.setText(a.getName());

        // address
        address.setText(a.getAddress());

        // rating
//        rating.setText("Rating: " + String.valueOf(a.getOverallRating()));
        rating.setText(String.format("%.2f",a.getOverallRating()));

        raters.setText(String.valueOf(a.getNumberOfRaters()));

//        // genre
//        String genreStr = "";
//        for (String str : a.getGenre()) {
//            genreStr += str + ", ";
//        }
//        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
//                genreStr.length() - 2) : genreStr;
//        genre.setText(genreStr);

        // operating hours
        operationHours.setText(String.valueOf(a.getOperatingHours()));

        return convertView;
    }

}