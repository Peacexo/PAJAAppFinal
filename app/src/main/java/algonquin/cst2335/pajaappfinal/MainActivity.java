package algonquin.cst2335.pajaappfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageButton dictionaryButton;
    ImageButton recipeButton;
    ImageButton sunriseButton;
    ImageButton deezerButton;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dictionaryButton = findViewById(R.id.main_dictionary);
        recipeButton = findViewById(R.id.main_recipe);
        sunriseButton = findViewById(R.id.main_sun);
        deezerButton = findViewById(R.id.main_deezer);

        dictionaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DictionaryMain.class));
            }
        });

        recipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecipeHomeActivity.class));
            }
        });

        sunriseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SunriseSunsetActivity.class));
            }
        });

        deezerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SongSearchActivity.class));
            }
        });
    }

//    public void openSunriseApp(View view) {
//        startActivity(new Intent(this,SunriseSunsetActivity.class));
//    }
//
//    public void openDictionaryApp(View view) {
//        startActivity(new Intent(this, DictionaryMain.class));
//    }
//
//    public void openRecipeApp(View view) {
//        startActivity(new Intent(this, RecipeHomeActivity.class));
//    }
//
//    public void openDeezerApp(View view) {
//        startActivity(new Intent(this, SongSearchActivity.class));
//    }
}
