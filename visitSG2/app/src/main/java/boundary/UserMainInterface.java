package boundary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.wong0903.visitsg.R;

/**
 * Created by wong0903 on 10/4/2018.
 */

public class UserMainInterface extends AppCompatActivity {

    EditText inputText;
    TextView responseView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermain);

        responseView = findViewById(R.id.responseView);
        inputText = findViewById(R.id.inputText);
        progressBar = findViewById(R.id.progressBar);
    }

}
