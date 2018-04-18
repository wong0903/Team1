package control;


import org.junit.Test;

import Database.AppDatabase;
import Database.AttractionDao;
import Database.UserDao;
import entity.Attraction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by wong0903 on 18/4/2018.
 */
public class RateReviewManagerTest {
    @Test
    public void calculateOverallRatingTest() throws Exception {

        double expected = 3.5;
        double output;
        double delta = 0.1;
        int input = 3;

        RateReviewManager mMockRateReviewManager = new RateReviewManager();
        AppDatabase mMockDB = mock(AppDatabase.class);

        AttractionDao mMockAttractionDao = mock(AttractionDao.class);
        int count = 2;
        double oldTotalRatings = 4;

        String attractionURL = "123";

        when(mMockDB.attractionDao()).thenReturn(mMockAttractionDao);
        when(mMockDB.attractionDao().getCountbyAttractionURL(attractionURL)).thenReturn(count);
        when(mMockDB.attractionDao().getOverallRatingByAttractionURL(attractionURL)).thenReturn(oldTotalRatings);

        output = mMockRateReviewManager.calculateOverallRating(mMockDB,attractionURL,input);
        assertEquals(expected,output, delta);

    }

}