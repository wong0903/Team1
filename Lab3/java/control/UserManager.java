package control;

import java.util.ArrayList;
import java.util.List;

import entity.User;

/**
 * Created by wong0903 on 20/3/2018.
 * This class creates a user and stores it in a local database.
 * This class allows user to log in and access to the system.
 */

public class UserManager {
    private String loginID;
    private String password;
    private boolean access;

    private List<User> userList = new ArrayList<User>();

    public void signUp(String loginID, String password){
        if(verifyLoginID(loginID) && verifyPassword(password)){
            User user = new User(loginID, password);
            userList.add(user);
            System.out.println("Sign Up successful");
        }else
            System.out.println("Sign Up unsuccessful");
    }

    public boolean login(String loginID, String password){
        access = validate(loginID, password);
        return access;
    }

    public boolean verifyLoginID(String loginID) {
        //if login ID is found in the local database
        if () {
            System.out.println("Someone has used the same ID. Please try again!");
            return false;
        }else if(loginID.length() < 1 && loginID.length() >20) {
            System.out.println("LoginID out of range. Please try again!");
            return false;
        }else
            return true;
    }

    public boolean verifyPassword(String password){
        if(password.matches("[A-Za-z0-9]+")){
            System.out.println("Password is not alphanumeric(etc abc12). Please try again!");
            return false;
        }else if( password.length() < 8 && password.length() > 20) {
            System.out.println("Password characters out of range. Please try again!");
            return false;
        }else
            return true;
    }

    public boolean validate(String loginID, String password){
        //if the loginID and password are found as a valid match pair in the local database
        if() {
            return true;
        }else
            return false;
    }
}
