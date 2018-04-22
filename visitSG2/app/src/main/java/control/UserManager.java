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
 * This class contains 5 key methods, signUp,login,verifyLoginID,verifyPassword and confirmPassword.
 *
 * signUp method will call the verifyLoginID, verifyPassword and confirmPassword method in the
 * stated order. It will return true if the input username and password passes all the three methods.
 *
 * login method will call to the user database and find if there is an existing user given the
 * user input loginID. If yes, match the input password with the password corresponding to the existing
 * loginID in the database and return true if it matches.
 *
 * verifyLoginID will call to the user database and find if there is an existing user given the user
 * input loginID. If there is an existing account/input loginID is out of range/empty input return
 * false. If there is no existing account, check for the length and check if its empty.
 *
 * verifyPassword will return false if the password is not alphanumeric and if the length of input password is
 * less than 8 or more than 20.
 *
 * confirmPassword will match the input passwords and return true if it matches
 *
 */

public class UserManager {

    public static boolean signUp(Context c, AppDatabase db, String loginID, String password1,String password2){
        if(verifyLoginID(c,db,loginID)){
            if(verifyPassword(c,password1)){
                if(confirmPassword(c,password1,password2)){
                    User user = new User(loginID, password1);
                    db.userDao().insertUser(user);
                    return true;
                }
            }
        }
        return false;
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

    public static boolean verifyLoginID(Context c, AppDatabase db, String loginID) {
        if(db.userDao().getAll().size() != 0) {
            User user = db.userDao().findByID(loginID);
            //if login ID is not found in the local database
            if (user != null) {
                toast(c, "Someone has used the same ID. Please try again!");
                return false;
            } else if (loginID.length() < 1 || loginID.length() > 20) {
                toast(c, "LoginID characters out of range(1-20words).Please try again!");
                return false;
            }else if(loginID.matches("")){
                toast(c,"You did not enter a username");
                return false;
            } else
                return true;
        } else if(loginID.length() < 1 || loginID.length() > 20) {
            return false;
        }else if(loginID.matches("")){
            toast(c,"You did not enter a username");
            return false;
        } else
            return true;
    }

    public static boolean verifyPassword(final Context c, String password){
        if(!(password.matches((".*[A-Za-z].*")) && password.matches(".*[0-9].*") && password.matches("[A-Za-z0-9]*"))){
            toast(c, "Password must be alphanumeric(etc \"abc123\". Please try again!");
            return false;
        }else if( password.length() < 8 || password.length() > 20) {
            toast(c, "Password must be 8-20words.Please try again!");
            return false;
        }else {
            return true;
        }
    }

    public static boolean confirmPassword(final Context c, String password1, String password2) {
        if (password1.matches(password2)) {
            return true;
        } else {
            toast(c, "Password is not the same.Please try again!");
            return false;
        }
    }

    public static void toast(final Context context, final String text) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
