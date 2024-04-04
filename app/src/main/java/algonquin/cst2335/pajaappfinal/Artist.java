package algonquin.cst2335.pajaappfinal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.math.BigInteger;

@Entity
public class Artist  {
    @PrimaryKey
    @ColumnInfo(name="id")
    private int id;
    @ColumnInfo(name="name")
    private String name;
    @ColumnInfo(name="poster")
    private String poster;

    private String tracklist;
    public Artist(String name, String poster, String tracklist){
        this.name = name;
        this.poster = poster;
        this.tracklist = tracklist;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

}
