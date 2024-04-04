package algonquin.cst2335.pajaappfinal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.math.BigInteger;
import java.util.ArrayList;


@Entity
public class Album   {
    @PrimaryKey
    @ColumnInfo(name="albumID")
    private int id;
    @ColumnInfo(name="title")
    private String name;
    @ColumnInfo(name="artist_name")
    private String  artist_name;
    @ColumnInfo(name="album_cover")
    private String cover;
    private String tracklist;
    @Ignore
    public Album (String name, String cover){

        this.name = name;
        this.cover = cover;
    }

    public Album(String name,  String cover, String tracklist) {
        this.name = name;
        this.tracklist = tracklist;
        this.cover = cover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artist_name;
    }

    public void setArtistName(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }
}
