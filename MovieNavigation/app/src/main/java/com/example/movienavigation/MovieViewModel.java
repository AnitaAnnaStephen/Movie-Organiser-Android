package com.example.movienavigation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository repository;
    private LiveData<List<Movie>> allMovies;
    private LiveData<List<Movie>> movie;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
        allMovies = repository.getAllMovies();
       // movie=repository.getMovie();
    }

    public void insert(Movie movie) {
        repository.insert(movie);
    }

    public void update(Movie movie) {
        repository.update(movie);
    }

    public void delete(Movie movie) {
        repository.delete(movie);
    }

    public void deleteAllMovies() {
        repository.deleteAllMovies();
    }

    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }

    /*public Movie getMovie(Movie movie) {
        return movie;
    }*/
}
