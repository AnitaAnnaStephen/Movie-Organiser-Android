package com.example.movienavigation;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert
    void insert(Movie movie);

    @Update
    void update(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("DELETE FROM movie_table")
    void deleteAllNotes();

    @Query("SELECT * FROM movie_table ORDER BY id DESC")
    LiveData<List<Movie>> getAllNotes();

    /*@Query("SELECT * FROM movie_table ")
    LiveData<List<Movie>> getMovie(Movie movie);*/
}
