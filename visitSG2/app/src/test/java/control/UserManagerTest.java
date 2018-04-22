package control;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.test.UiThreadTest;

import org.junit.Before;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.mockito.Mockito.mock;
//import org.mockito.Mockito.when;
//import org.mockito.Mockito.*;

import java.util.List;

import Database.AppDatabase;
import Database.UserDao;
import entity.User;

//import static control.AndroidMockUtil.mockMainThreadHandler;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by nigelleong on 18/4/18.
 */

public class UserManagerTest {

//    Context c = Mockito.mock(Context.class);

    @Before
    public void setUp() throws Exception {
//        mockMainThreadHandler();
    }

//    @PrepareForTest({Looper.class,UserManager.class})

    @Test
    public void signUpTest() throws Exception {

        Boolean expected = true;
        Boolean output;

        Context mMockContext = mock(Context.class);
        AppDatabase mMockDB = mock(AppDatabase.class);
//        User mMockUser = mock(User.class);
        User mMockUser = null;
        UserDao mMockUserDao = mock(UserDao.class);
        List<User> mMockListUser = mock(List.class);
        UserManager mMockUserManager = new UserManager();

        String loginID = "user1";
        String password1 = "abcd123";
        String password2 = "abcd123";

        when(mMockDB.userDao()).thenReturn(mMockUserDao);
        when(mMockDB.userDao().getAll()).thenReturn(mMockListUser);
        when(mMockDB.userDao().getAll().size()).thenReturn(1);
        when(mMockDB.userDao().findByID(loginID)).thenReturn(mMockUser);

        output = mMockUserManager.signUp(mMockContext, mMockDB, loginID, password1, password2);

        assertEquals(expected, output);
    }

}