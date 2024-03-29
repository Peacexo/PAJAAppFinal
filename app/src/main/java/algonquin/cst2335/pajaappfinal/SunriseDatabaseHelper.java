package algonquin.cst2335.pajaappfinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SunriseDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Location.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_location";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CITY = "location_city";
    private static final String COLUMN_LAT = "location_latitude";
    private static final String COLUMN_LON = "location_longitude";
    public SunriseDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTERGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CITY + " TEXT, " +
                COLUMN_LAT + " TEXT, " +
                COLUMN_LON + " INTERGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
