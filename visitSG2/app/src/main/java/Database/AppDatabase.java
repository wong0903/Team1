package Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import entity.Attraction;
import entity.LoggedInUser;
import entity.RatingAndReview;
import entity.User;

/**
 * Created by wong0903 on 10/4/2018.
 */


@Database(entities = {User.class, Attraction.class, RatingAndReview.class, LoggedInUser.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();

    public abstract RatingAndReviewDao ratingAndReviewDao();

    public abstract AttractionDao attractionDao();

    public abstract LoggedInUserDao loggedInUserDao();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE OverallRating "
                    + " ADD COLUMN count INTEGER NOT NULL DEFAULT 0");
        }
    };

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            //.addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                            .build();
        }
        return INSTANCE;
    }



    public static void destroyInstance() {
        INSTANCE = null;
    }

}
