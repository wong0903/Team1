package boundary;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    EditText txtPassword, txtName, txtConfirmPass;
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

        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = txtName.getText().toString();
                final String password1 = txtPassword.getText().toString();
                final String password2 = txtConfirmPass.getText().toString();

                if (task != null) return;
                task = new SignUpTask(username, password1, password2);
                task.execute((Void) null);
            }
        });
    }

    public class SignUpTask extends AsyncTask<Void, Void, Boolean> {
        private final String mUsername;
        private final String mPassword;
        private final String cPassword;
        UserManager userManager = new UserManager();
        SignUpTask(String username, String password1, String password2) {
            mUsername = username;
            mPassword = password1;
            cPassword = password2;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            if((userManager.verifyLoginID(getApplicationContext(),db,mUsername))){
                if((userManager.verifyPassword(getApplicationContext(), mPassword))) {
                    if ((userManager.confirmPassword(getApplicationContext(),mPassword, cPassword))) {
                        userManager.signUp(db, mUsername, mPassword);
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            task = null;
            if (success) {
                Toast.makeText(getApplicationContext(), "Sign Up successful", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        @Override
        protected void onCancelled() {
            task = null;
        }
    }
}




