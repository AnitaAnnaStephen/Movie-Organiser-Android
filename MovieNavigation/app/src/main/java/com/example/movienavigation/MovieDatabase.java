package com.example.movienavigation;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    private static MovieDatabase instance;

    public abstract MovieDao movieDao();

    public static synchronized MovieDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MovieDatabase.class, "movie_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private MovieDao movieDao;

        private PopulateDbAsyncTask(MovieDatabase db) {
            movieDao = db.movieDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            movieDao.insert(new Movie("Title 1", "Year 1", "Language 1","Cast 1","Summary 1","Link 1","Category 1"));
            movieDao.insert(new Movie("Title 2", "Year 2", "Language 2","Cast 2","Summary 2","Link 2","Category 2"));
            movieDao.insert(new Movie("Title 3", "Year 3", "Language 3","Cast 3","Summary 3","Link 3","Category 3"));
            return null;
        }
    }


}

