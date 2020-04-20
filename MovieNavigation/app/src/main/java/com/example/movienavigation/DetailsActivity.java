package com.example.movienavigation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    public static final String DETAIL_ID =
            "com.example.movienavigation.EXTRA_ID";
    public static final String DETAIL_TITLE =
            "com.example.movienavigation.EXTRA_TITLE";
    public static final String DETAIL_DESCRIPTION =
            "com.example.movienavigation.EXTRA_DESCRIPTION";
    public static final String DETAIL_YEAR =
            "com.example.movienavigation.EXTRA_YEAR";
    public static final String DETAIL_CAST =
            "com.example.movienavigation.EXTRA_CAST";
    public static final String DETAIL_LANGUAGE =
            "com.example.movienavigation.EXTRA_LANGUAGE";
    public static final String DETAIL_LINK =
            "com.example.movienavigation.EXTRA_LINK";
    public static final String DETAIL_CATEGORY =
            "com.example.movienavigation.EXTRA_CATEGORY";
    private static final int DETAIL_IMAGE_CODE =1 ;
    private TextView TextTitle;
    private TextView TextTitle1;
    private TextView TextDescription;
    private TextView TextYear;
    private TextView TextLanguage;
    private TextView TextCast;
    private TextView TextLink;
    private Button btnEdit;
    public ImageView editImage;
    private MovieViewModel movieViewModel ;
    public static final int EDIT_MOVIE_REQUEST = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        TextTitle = findViewById(R.id.txt_Title);
        TextTitle1 = findViewById(R.id.txt_Title1);
        TextDescription = findViewById(R.id.txt_Summary);
        TextCast=findViewById(R.id.txt_Cast);
        TextLanguage = findViewById(R.id.txt_Language);
        TextLink = findViewById(R.id.txt_Link);
        TextYear=findViewById(R.id.txt_Year);
        btnEdit=findViewById(R.id.bt_edit);
        //editImage=findViewById(R.id.img);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent=getIntent();
        if (intent.hasExtra(DETAIL_ID)) {
            setTitle("Movie Details");
            TextTitle1.setText(intent.getStringExtra(DETAIL_TITLE));
            TextTitle.setText(intent.getStringExtra(DETAIL_TITLE));
           TextDescription.setText(intent.getStringExtra(DETAIL_DESCRIPTION));
            TextCast.setText(intent.getStringExtra(DETAIL_CAST));
           TextLink.setText(intent.getStringExtra(DETAIL_LINK));
            TextLanguage.setText(intent.getStringExtra(DETAIL_LANGUAGE));
            TextYear.setText(intent.getStringExtra(DETAIL_YEAR));

        }
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

       /* btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent intent=new Intent(DetailsActivity.this,AddMovieActivity.class);
                startActivityForResult(intent,EDIT_MOVIE_REQUEST);

            }
        });*/
        //RecyclerView recyclerView = findViewById(R.id.recyclerView_detail);

        final MovieAdapter2 adapter = new MovieAdapter2();
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        movieViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                //update RecyclerView
                adapter.submitList(movies);
                // Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });
        //recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MovieAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent intent = new Intent(DetailsActivity.this, AddMovieActivity.class);
                intent.putExtra(AddMovieActivity.EXTRA_ID, movie.getId());
                intent.putExtra(AddMovieActivity.EXTRA_TITLE, movie.getTitle());
                intent.putExtra(AddMovieActivity.EXTRA_DESCRIPTION, movie.getSummary());
                intent.putExtra(AddMovieActivity.EXTRA_LANGUAGE, movie.getLanguage());
                intent.putExtra(AddMovieActivity.EXTRA_CAST, movie.getCast());
                intent.putExtra(AddMovieActivity.EXTRA_LINK, movie.getLink());
                intent.putExtra(AddMovieActivity.EXTRA_YEAR, movie.getYear());
                intent.putExtra(AddMovieActivity.EXTRA_CATEGORY, movie.getCategory());
                startActivityForResult(intent, EDIT_MOVIE_REQUEST);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_MOVIE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(DetailsActivity.DETAIL_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Movie can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(AddMovieActivity.EXTRA_TITLE);
            String year = data.getStringExtra(AddMovieActivity.EXTRA_YEAR);
            String link = data.getStringExtra(AddMovieActivity.EXTRA_LINK);
            String description = data.getStringExtra(AddMovieActivity.EXTRA_DESCRIPTION);
            String language = data.getStringExtra(AddMovieActivity.EXTRA_LANGUAGE);
            String cast = data.getStringExtra(AddMovieActivity.EXTRA_CAST);
            String category = data.getStringExtra(AddMovieActivity.EXTRA_CATEGORY);
            Movie movie = new Movie(title, year, language, cast, description, link,category);
            movie.setId(id);
            movieViewModel.update(movie);

            Toast.makeText(this, "Movie updated", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Movie not Updated", Toast.LENGTH_SHORT).show();
        }
    }

}
