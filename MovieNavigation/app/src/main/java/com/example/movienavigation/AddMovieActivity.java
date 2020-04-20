package com.example.movienavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddMovieActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.example.movienavigation.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.movienavigation.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.movienavigation.EXTRA_DESCRIPTION";
    public static final String EXTRA_YEAR =
            "com.example.movienavigation.EXTRA_YEAR";
    public static final String EXTRA_CAST =
            "com.example.movienavigation.EXTRA_CAST";
    public static final String EXTRA_LANGUAGE =
            "com.example.movienavigation.EXTRA_LANGUAGE";
    public static final String EXTRA_LINK =
            "com.example.movienavigation.EXTRA_LINK";
    public static final String EXTRA_CATEGORY =
            "com.example.movienavigation.EXTRA_CATEGORY";
    private static final int EXTRA_IMAGE_CODE =1 ;

    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextYear;
    private EditText editTextLanguage;
    private EditText editTextCast;
    private EditText editTextLink;
    private EditText editTextCategory;
    public ImageView editImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_movie);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextCast=findViewById(R.id.edit_text_cast);
        editTextLanguage = findViewById(R.id.edit_text_language);
        editTextLink = findViewById(R.id.edit_text_link);
        editTextYear=findViewById(R.id.edit_text_year);
        editTextCategory=findViewById(R.id.edit_text_category);
        //editImage=findViewById(R.id.img);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent=getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Movie");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            editTextCast.setText(intent.getStringExtra(EXTRA_CAST));
            editTextLink.setText(intent.getStringExtra(EXTRA_LINK));
            editTextLanguage.setText(intent.getStringExtra(EXTRA_LANGUAGE));
            editTextYear.setText(intent.getStringExtra(EXTRA_YEAR));
            editTextCategory.setText(intent.getStringExtra(EXTRA_CATEGORY));
        } else {
            setTitle("Add New Movie");
            /*Intent imgintent = new Intent();
            imgintent.setType("image/*");
            imgintent.setAction(imgintent.ACTION_GET_CONTENT);
            startActivityForResult(imgintent.createChooser(intent, "select a picture"), EXTRA_IMAGE_CODE);*/

        }
    }
    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String link = editTextLink.getText().toString();
        String year = editTextYear.getText().toString();
        String language = editTextLanguage.getText().toString();
        String cast = editTextCast.getText().toString();
        String category=editTextCategory.getText().toString();
        if (title.trim().isEmpty()){
            Toast.makeText(this, "Please insert a title", Toast.LENGTH_SHORT).show();
            return;
        }

        else if ( year.trim().isEmpty()) {
            Toast.makeText(this, "Please add a year", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (language.trim().isEmpty()){
            Toast.makeText(this, "Please add language", Toast.LENGTH_SHORT).show();
            return;
        }

        else if ( cast.trim().isEmpty()) {
            Toast.makeText(this, "Please insert cast details", Toast.LENGTH_SHORT).show();
            return;
        }
        if (description.trim().isEmpty()){
            Toast.makeText(this, "Please insert movie summary", Toast.LENGTH_SHORT).show();
            return;
        }

        else if ( link.trim().isEmpty()) {
            Toast.makeText(this, "Please insert available links", Toast.LENGTH_SHORT).show();
            return;
        }
        else if ( category.trim().isEmpty()) {

            Toast.makeText(this, "Please insert category", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_YEAR, year);
        data.putExtra(EXTRA_LINK, link);
        data.putExtra(EXTRA_LANGUAGE, language);
        data.putExtra(EXTRA_CAST, cast);
        data.putExtra(EXTRA_CATEGORY, category);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_new_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_movie:
                saveNote();
                //Toast.makeText(this, "Movie is Added", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
