package boundary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wong0903.visitsg.R;

import Database.AppDatabase;
import control.UserManager;
import helper.SessionManager;

/**
 * Created by wong0903 on 20/3/2018.
 * This class display a login screen and allows
 * user to input their login ID and password.
 */

public class LoginInterface extends AppCompatActivity implements View.OnClickListener {

    EditText txtPassword,txtName;
    String username,password;
    ProgressDialog pDialog;
    SessionManager session;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtName = findViewById(R.id.username);
        txtPassword = findViewById(R.id.password);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnSignUp = findViewById(R.id.btnSignUp);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        db = AppDatabase.getAppDatabase(getApplicationContext());


        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginInterface.this, SearchInterface.class);
            startActivity(intent);
        }

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnLogin:
                username = txtName.getText().toString();
                password = txtPassword.getText().toString();
                if (UserManager.login(db, username, password)) {
                    session.setLogin(true);
                    Toast.makeText(getApplicationContext(), "Redirecting...",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, SearchInterface.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"error" , Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnSignUp:
                Intent intent = new Intent(this, SignUpInterface.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
