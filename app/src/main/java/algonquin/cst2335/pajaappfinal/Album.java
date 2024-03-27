package algonquin.cst2335.pajaappfinal;

import java.util.ArrayList;



public class Album {

    private String name, artist_name, cover;
    private ArrayList<Song> songList;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Album(String name, String artist_name, ArrayList<Song> songList, String cover) {
        this.name = name;
        this.artist_name = artist_name;
        this.songList = songList;
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public ArrayList<Song> getSongList() {
        return songList;
    }

    public void setSongList(ArrayList<Song> songList) {
        this.songList = songList;
    }
}
