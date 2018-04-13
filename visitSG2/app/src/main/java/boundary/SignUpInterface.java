package boundary;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wong0903.visitsg.R;

import Database.AppDatabase;
import helper.SessionManager;

/**
 * Created by wong0903 on 10/4/2018.
 */

public class SignUpInterface extends AppCompatActivity {
    EditText txtPassword, txtName, txtConfirmPass, txtEmail;
    ProgressDialog pDialog;
    SessionManager session;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        txtName = findViewById(R.id.name_sign);
        txtPassword = findViewById(R.id.pass_sign);
        txtConfirmPass = findViewById(R.id.confirm_pass);
        txtEmail = findViewById(R.id.email_sign);

        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String username = txtName.getText().toString();
                        final String password1 = txtPassword.getText().toString();
                        final String password2 = txtConfirmPass.getText().toString();
                        final String email = txtEmail.getText().toString();
                        if (control.UserManager.verifyLoginID(db, username)) {
                            if (control.UserManager.verifyPassword(password1)) {
                                if (control.UserManager.confirmPassword(password1, password2)) {
                                    control.UserManager.signUp(db, username, password1, email);
                                    Toast.makeText(getApplicationContext(), "Sign Up successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUpInterface.this, SearchInterface.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                } else
                                    Toast.makeText(getApplicationContext(), "Your password is different", Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(getApplicationContext(), "Password must be alphanumeric/" +
                                        "Password length must be 8-20words ", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(getApplicationContext(), "Someone has already used the same username/" +
                                    "Username length must be 1-20words", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        //});
    }
}




