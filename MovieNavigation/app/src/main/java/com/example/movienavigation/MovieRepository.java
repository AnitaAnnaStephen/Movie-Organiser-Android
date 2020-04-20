package com.example.movienavigation;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieRepository {
    private MovieDao movieDao;
    private LiveData<List<Movie>> allMovies;
    private LiveData<List<Movie>> moviedetail;
   // private int id=0;
    public MovieRepository(Application application) {
        MovieDatabase database = MovieDatabase.getInstance(application);
        movieDao = database.movieDao();
        allMovies = movieDao.getAllNotes();

       // moviedetail=movieDao.getMovie(Movie movie);
    }

    public void insert(Movie movie) {
        new InsertNoteAsyncTask(movieDao).execute(movie);
    }

    public void update(Movie movie) {
        new UpdateNoteAsyncTask(movieDao).execute(movie);
    }

    public void delete(Movie movie) {
        new DeleteNoteAsyncTask(movieDao).execute(movie);
    }

    public void deleteAllMovies() {
        new DeleteAllNotesAsyncTask(movieDao).execute();
    }

    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }
    public LiveData<List<Movie>> getMovie(Movie movie) {
        return moviedetail;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDao movieDao;

        private InsertNoteAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.insert(movies[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDao movieDao;

        private UpdateNoteAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.update(movies[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDao movieDao;

        private DeleteNoteAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.delete(movies[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private MovieDao movieDao;

        private DeleteAllNotesAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            movieDao.deleteAllNotes();
            return null;
        }
    }
}
