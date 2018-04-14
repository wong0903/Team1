package Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import entity.OverallRating;
import entity.RatingAndReview;
import entity.User;

/**
 * Created by wong0903 on 10/4/2018.
 */


@Database(entities = {User.class, OverallRating.class, RatingAndReview.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();

    public abstract RatingAndReviewDao ratingAndReviewDao();
    public abstract OverallRatingDao overallRatingDao();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .addMigrations(MIGRATION_1_2)
                            .build();
        }
        return INSTANCE;
    }



    public static void destroyInstance() {
        INSTANCE = null;
    }

}
