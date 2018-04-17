package control;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import Database.AppDatabase;
import entity.User;

/**
 * Created by wong0903 on 20/3/2018.
 * This class creates a user and stores it in a local database.
 * This class allows user to log in and access to the system.
 */

public class UserManager {

    public static void signUp(AppDatabase db, String loginID, String password){
            User user = new User(loginID, password);
            db.userDao().insertUser(user);
    }

    public static boolean login(AppDatabase db, String loginID, String password){
        User user = db.userDao().findByID(loginID);
        if(user != null){
            if (user.getPassword().equals(password)) {
                return true;
            } else
                return false;
        } else
            return false;
    }

    public boolean verifyLoginID(Context c, AppDatabase db, String loginID) {
        if(db.userDao().getAll().size() != 0) {
            User user = db.userDao().findByID(loginID);
            //if login ID is not found in the local database
            if (user != null) {
                toast(c, "Someone has used the same ID. Please try again!");
                return false;
            } else if (loginID.length() < 1 && loginID.length() > 20) {
                toast(c, "LoginID characters out of range(1-20words).Please try again!");
                return false;
            }else if(loginID.matches("")){
                toast(c,"You did not enter a username");
                return false;
            } else
                return true;
        } else if(loginID.length() < 1 && loginID.length() > 201) {
            Log.d("name", String.valueOf(loginID.length()));
            return false;
        }else if(loginID.matches("")){
            toast(c,"You did not enter a username");
            return false;
        } else
            return true;
    }

    public boolean verifyPassword(final Context c, String password){
        if(!(password.matches((".*[A-Za-z].*")) && password.matches(".*[0-9].*") && password.matches("[A-Za-z0-9]*"))){
            toast(c, "Password must be alphanumeric(etc \"abc123\". Please try again!");
            return false;
        }else if( password.length() < 8 && password.length() > 20) {
            toast(c, "Password characters out of range(1-20words).Please try again!");
            return false;
        }else
            return true;
    }

    public boolean confirmPassword(final Context c, String password1, String password2) {
        if (password1.matches(password2)) {
            return true;
        } else {
            toast(c, "Password is not the same.Please try again!");
            return false;
        }
    }

    public void toast(final Context context, final String text) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
