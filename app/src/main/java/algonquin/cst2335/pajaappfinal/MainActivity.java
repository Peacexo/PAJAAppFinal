package algonquin.cst2335.pajaappfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void openSunriseApp(View view) {
        startActivity(new Intent(this,SunriseSunsetActivity.class));

    }
    public void openDictionaryApp(View view) {
        startActivity(new Intent(this, DictionaryMain.class));
    }

    public void openRecipeApp(View view) {
        startActivity(new Intent(this, RecipeHomeActivity.class));
    }

    public void openDeezerApp(View view) {
        startActivity(new Intent(this, SongSearchActivity.class));
    }



}