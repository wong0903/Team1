package boundary;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wong0903.visitsg.R;

import Database.AppDatabase;
import control.UserManager;

/**
 * Created by wong0903 on 10/4/2018.
 */

public class SignUpInterface extends AppCompatActivity {
    EditText txtPassword, txtName, txtConfirmPass, txtEmail;
    ProgressDialog pDialog;
    private SignUpTask task = null;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = AppDatabase.getAppDatabase(getApplicationContext());

        txtName = findViewById(R.id.name_sign);
        txtPassword = findViewById(R.id.pass_sign);
        txtConfirmPass = findViewById(R.id.confirm_pass);
        txtEmail = findViewById(R.id.email_sign);

        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = txtName.getText().toString();
                final String password1 = txtPassword.getText().toString();
                final String password2 = txtConfirmPass.getText().toString();
                final String email = txtEmail.getText().toString();

                if (task != null) return;
                task = new SignUpTask(username, password1, password2, email, getApplicationContext());
                task.execute((Void) null);
            }
        });
    }

    public class SignUpTask extends AsyncTask<Void, Void, Boolean> {
        private final String mUsername;
        private final String mEmail;
        private final String mPassword;
        private final String cPassword;

        SignUpTask(String username, String password1, String password2, String mail,Context c) {
            mUsername = username;
            mEmail = mail;
            mPassword = password1;
            cPassword = password2;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            if(!(UserManager.verifyLoginID(db,mUsername) && UserManager.verifyPassword(mPassword)
                    && UserManager.confirmPassword(mPassword,cPassword)))
                return false;
            UserManager.signUp(db, mUsername, mPassword, mEmail);
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            task = null;
            if (success) {
                Toast.makeText(getApplicationContext(), "Sign Up successful", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
                Toast.makeText(getApplicationContext(), "Sign Up failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            task = null;
        }
    }
}




