package algonquin.cst2335.pajaappfinal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**
 * The FavoriteLocation class represents a single favorite location entry in the database.
 * Each entry contains information such as location name, sunrise time, sunset time, latitude, longitude, and favorite status.
 *
 * @author JingYi Li
 */
@Entity(tableName = "favorite_locations")
public class FavoriteLocation {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "location_name")
    public String locationName;

    @ColumnInfo(name = "sunrise_time") // Adjusted to match expected column name
    public String sunriseTime;

    @ColumnInfo(name = "sunset_time") // Adjusted to match expected column name
    public String sunsetTime;

    @ColumnInfo(name = "is_favorite")
    public boolean isFavorite;

    @ColumnInfo(name = "latitude")
    public double latitude;

    @ColumnInfo(name = "longitude")
    public double longitude;

    // Constructor
    public FavoriteLocation(String locationName, String sunriseTime, String sunsetTime) {
        this.locationName = locationName;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getSunriseTime() { // Adjusted to match column name
        return sunriseTime;
    }

    public String getSunsetTime() { // Adjusted to match column name
        return sunsetTime;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setSunriseTime(String sunriseTime) { // Adjusted to match column name
        this.sunriseTime = sunriseTime;
    }

    public void setSunsetTime(String sunsetTime) { // Adjusted to match column name
        this.sunsetTime = sunsetTime;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
