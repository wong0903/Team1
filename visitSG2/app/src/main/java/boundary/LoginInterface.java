package boundary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wong0903.visitsg.R;

import Database.AppDatabase;
import control.UserManager;
import entity.LoggedInUser;

/**
 * Created by wong0903 on 20/3/2018.
 * This class display a login screen and allows
 * user to input their login ID and password.
 */

public class LoginInterface extends AppCompatActivity implements View.OnClickListener {

    EditText txtPassword,txtName;
    String username,password;
    TextView txtSignUp;
    ProgressDialog pDialog;
    private AppDatabase db;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtName = findViewById(R.id.username);
        txtPassword = findViewById(R.id.password);

        Button btnLogin = findViewById(R.id.btnLogin);
        txtSignUp = findViewById(R.id.txt_signup);
//        Button btnSignUp = findViewById(R.id.btnSignUp);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        db = AppDatabase.getAppDatabase(getApplicationContext());

        sp = getSharedPreferences("login",MODE_PRIVATE);

        // Check if user is already logged in or not

        if(sp.getBoolean("logged",false)){
            Intent intent = new Intent(LoginInterface.this, UserInterface.class);
            intent.putExtra("user",db.loggedInUserDao().getUser());
            startActivity(intent);
            finish();
        }


        btnLogin.setOnClickListener(this);
        //btnSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnLogin:
                username = txtName.getText().toString();
                password = txtPassword.getText().toString();
                if (UserManager.login(db, username, password)) {
                    LoggedInUser user = new LoggedInUser(username,password);
                    db.loggedInUserDao().insertUser(user);
                    Toast.makeText(getApplicationContext(), "Redirecting...",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainInterface.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    sp.edit().putBoolean("logged",true).apply();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"Username/Password invalid" , Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.txt_signup:
                Intent intent = new Intent(this, SignUpInterface.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
