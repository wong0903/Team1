package control;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.wong0903.visitsg.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import boundary.AttractionInterface;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;

/**
 * Created by wong0903 on 18/4/2018.
 */

@RunWith(AndroidJUnit4.class)
public class AttractionInterfaceTest {

    @Rule
    public ActivityTestRule<AttractionInterface> activityTestRule =
            new ActivityTestRule<>(AttractionInterface.class);

    @Test
    public void testRating() throws Exception{
        onView(withId(R.id.btn_rating)).perform(click());
        onView(withId(R.id.submitRateBtn)).perform(click());
        assertTrue(new AttractionInterface().getDialog().isShowing());
    }

}
