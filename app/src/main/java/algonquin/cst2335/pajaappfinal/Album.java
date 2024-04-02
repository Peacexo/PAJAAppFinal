package algonquin.cst2335.pajaappfinal;

import java.util.ArrayList;



public class Album   {

    private String name, artist_name, cover, tracklist;




    public Album (String name, String cover){

        this.name = name;
        this.cover = cover;
    }

    public Album(String name,  String cover, String tracklist) {
        this.name = name;
        this.tracklist = tracklist;
        this.cover = cover;
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
