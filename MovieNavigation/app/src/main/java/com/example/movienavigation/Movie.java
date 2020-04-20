package com.example.movienavigation;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "movie_table")
public class Movie {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String year;

    private String language;

    private String cast;

    private String summary;

    private String link;
    private String category;

    public Movie(String title, String year,String language, String cast,String summary, String link,String category) {
        this.title = title;
        this.year = year;
        this.language = language;
        this.cast = cast;
        this.summary = summary;
        this.link = link;
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getLanguage() {
        return language;
    }
    public String getCast() {
        return cast;
    }

    public String getSummary() {
        return summary;
    }

    public String getLink() {
        return link;
    }
    public String getCategory() {
        return category;
    }
}
