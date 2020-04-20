package com.example.movienavigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;

   public static final int ADD_MOVIE_REQUEST = 1;
   public static final int EDIT_MOVIE_REQUEST = 2;
    public static final int VIEW_MOVIE_REQUEST = 3;
    private MovieViewModel movieViewModel ;

    private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       RecyclerView recyclerView = findViewById(R.id.recyclerView);

       recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        FloatingActionButton fab = findViewById(R.id.button_add_movie);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,AddMovieActivity.class);
                startActivityForResult(intent,ADD_MOVIE_REQUEST);

            }
        });



        /* drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_fav, R.id.nav_tvshows,R.id.nav_wishlist)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);*/
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

       navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final MovieAdapter adapter = new MovieAdapter();
        recyclerView.setAdapter(adapter);
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        movieViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                //update RecyclerView
                adapter.submitList(movies);
                // Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });
        /*adapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra(DetailsActivity.DETAIL_ID, movie.getId());
                intent.putExtra(DetailsActivity.DETAIL_TITLE, movie.getTitle());
                intent.putExtra(DetailsActivity.DETAIL_DESCRIPTION, movie.getSummary());
                intent.putExtra(DetailsActivity.DETAIL_LANGUAGE, movie.getLanguage());
                intent.putExtra(DetailsActivity.DETAIL_CAST, movie.getCast());
                intent.putExtra(DetailsActivity.DETAIL_LINK, movie.getLink());
                intent.putExtra(DetailsActivity.DETAIL_YEAR, movie.getYear());
                startActivityForResult(intent, VIEW_MOVIE_REQUEST);
            }
        });*/
        adapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent intent = new Intent(MainActivity.this, AddMovieActivity.class);
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
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                movieViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Movie deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_MOVIE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddMovieActivity.EXTRA_TITLE);
            String year = data.getStringExtra(AddMovieActivity.EXTRA_YEAR);
            String link = data.getStringExtra(AddMovieActivity.EXTRA_LINK);
            String description = data.getStringExtra(AddMovieActivity.EXTRA_DESCRIPTION);
            String language = data.getStringExtra(AddMovieActivity.EXTRA_LANGUAGE);
            String cast = data.getStringExtra(AddMovieActivity.EXTRA_CAST);
            String category = data.getStringExtra(AddMovieActivity.EXTRA_CATEGORY);
            Movie movie = new Movie(title, year, language, cast, description, link,category);
            movieViewModel.insert(movie);

            Toast.makeText(this, "Movie Added", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == EDIT_MOVIE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddMovieActivity.EXTRA_ID, -1);

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
            Toast.makeText(this, "Movie not saved", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_notes:
                movieViewModel.deleteAllMovies();
                Toast.makeText(this, "All movies deleted", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.add_movies:
                Intent intent=new Intent(MainActivity.this,AddMovieActivity.class);
                startActivityForResult(intent,ADD_MOVIE_REQUEST);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){

            case R.id.nav_home:
                Intent m= new Intent(MainActivity.this,MainActivity.class);
                startActivity(m);
                break;
            case R.id.nav_add:
                Intent a= new Intent(MainActivity.this,AddMovieActivity.class);
                startActivity(a);
                break;
            case R.id.nav_fav:
                Intent f= new Intent(MainActivity.this,Favorites.class);
                startActivity(f);
                break;
            case R.id.nav_wishlist:
                Intent w= new Intent(MainActivity.this,WishList.class);
                startActivity(w);
                break;
            case R.id.nav_tvshows:
                Intent t= new Intent(MainActivity.this,TvShows.class);
                startActivity(t);

                break;
            case R.id.nav_recent:
                Intent r= new Intent(MainActivity.this,RecentMovies.class);
                startActivity(r);

                break;
            case R.id.nav_settings:
                Intent s= new Intent(MainActivity.this,Settings.class);
                startActivity(s);

                break;
            case R.id.nav_help:
                Intent h= new Intent(MainActivity.this,Help.class);
                startActivity(h);

                break;

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
