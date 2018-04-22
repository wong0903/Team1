package boundary;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.wong0903.visitsg.R;

/**
 * Created by wong0903 on 22/4/2018.
 * This class will display a dialog box which allows user to rate and review an attraction.
 */

public class RateReviewInterface extends Dialog {


    RatingBar ratingBar;
    TextView value, title;
    String review;
// Button yes_btn, no_btn;

    public RateReviewInterface(Context context)
    {
        super(context);
        setContentView(R.layout.ratingbox); // a simple layout with a TextView and Two Buttons
        ratingBar = (RatingBar)findViewById(R.id.ratingsBar);
        value = findViewById(R.id.rateValue);
        title = findViewById(R.id.rateHeader);
        EditText txtReview = findViewById(R.id.reviewED);
        review = txtReview.getText().toString();

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                value.setText(String.format("%.2f",v));
            }
        });
    }

    public String getReviewText(){
        return review;
    }

    public int getRatingValue(){
        return (int) ratingBar.getRating();
    }

    public void quitDialog(View v) {
        if (isShowing()) dismiss();
    }

    public void setTitle(String name) {
        title.setText(name);
    }


}
