package control;

import java.util.ArrayList;
import java.util.List;

import entity.User;

/**
 * Created by wong0903 on 20/3/2018.
 * This class creates a user and stores it in a list.
 */

public class UserManager {
    private String loginID;
    private String password;
    private boolean access;

    private List<User> userList = new ArrayList<User>();

    public void signUp(String loginID, String password){
        User user = new User(loginID, password);
    }
}
