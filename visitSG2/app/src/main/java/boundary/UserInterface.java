package boundary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wong0903.visitsg.R;

import Database.AppDatabase;
import entity.LoggedInUser;
import helper.SessionManager;

/**
 * Created by wong0903 on 17/4/2018.
 * This class will display the Login Interface if the user is not logged in.
 * Otherwise, it will display the user information.
 */

public class UserInterface extends AppCompatActivity {
    LoggedInUser user;
    AppDatabase db;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        db = AppDatabase.getAppDatabase(getApplicationContext());
        Intent i = getIntent();
        user = i.getExtras().getParcelable("user");
        TextView username = findViewById(R.id.displayName);
        username.setText("Username: " + user.getLoginID());
        Button logout = findViewById(R.id.btnLogout);

        session = new SessionManager(getApplicationContext());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.setLogin(false);
                db.loggedInUserDao().delete(user);
                Intent intent = new Intent(UserInterface.this, MainInterface.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
