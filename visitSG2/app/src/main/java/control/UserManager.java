package control;

import Database.AppDatabase;
import entity.User;

/**
 * Created by wong0903 on 20/3/2018.
 * This class creates a user and stores it in a local database.
 * This class allows user to log in and access to the system.
 */

public class UserManager {

    public static void signUp(AppDatabase db, String loginID, String password, String email){
            User user = new User(loginID, password, email);
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

    public static boolean verifyLoginID(AppDatabase db, String loginID) {
        if(db != null) {
            User user = db.userDao().findByID(loginID);
            //if login ID is not found in the local database
            if (user != null) {
                return false;
            } else if (loginID.length() < 1 && loginID.length() > 20) {
                return false;
            } else
                return true;
        }
        else
            return true;
    }

    public static boolean verifyPassword(String password){
        if(!password.matches("[A-Za-z0-9]+")){
            return false;
        }else if( password.length() < 8 && password.length() > 20) {
            return false;
        }else
            return true;
    }

    public static boolean confirmPassword(String password1, String password2) {
        if (password1.matches(password2)) {
            return true;
        } else
            return false;
    }

}
