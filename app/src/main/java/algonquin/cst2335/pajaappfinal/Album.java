/*
-------------------------------------------------------
Course: CST 2335 - Mobile Graphical Interface Programming
Final Project: Deezer Song Search API
Student Name: Allan Torres
Student Number: 041022473
-------------------------------------------------------
*/

package algonquin.cst2335.pajaappfinal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Represents an album retrieved from the Deezer Song Search API.
 * Each album has an ID, title, artist name, album cover URL, and tracklist URL.
 */
@Entity
public class Album {
    /**
     * The unique identifier for the album.
     */
    @PrimaryKey
    @ColumnInfo(name="albumID")
    private int id;

    /**
     * The title of the album.
     */
    @ColumnInfo(name="title")
    private String name;

    /**
     * The name of the artist who created the album.
     */
    @ColumnInfo(name="artist_name")
    private String artistName;

    /**
     * The URL of the album cover image.
     */
    @ColumnInfo(name="album_cover")
    private String cover;

    /**
     * The URL of the tracklist for the album.
     */
    @ColumnInfo(name="tracklist")
    private String tracklist;

    /**
     * Constructs a new Album object with only the name and cover URL.
     * This constructor is marked with @Ignore as it's not used for Room database operations.
     *
     * @param name  The title of the album.
     * @param cover The URL of the album cover image.
     */
    @Ignore
    public Album(String name, String cover) {
        this.name = name;
        this.cover = cover;
    }

    /**
     * Constructs a new Album object with the specified details.
     *
     * @param name      The title of the album.
     * @param cover     The URL of the album cover image.
     * @param tracklist The URL of the tracklist for the album.
     */
    public Album(String name, String cover, String tracklist) {
        this.name = name;
        this.tracklist = tracklist;
        this.cover = cover;
    }

    /**
     * Gets the ID of the album.
     *
     * @return The ID of the album.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the album.
     *
     * @param id The ID of the album.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the cover URL of the album.
     *
     * @return The cover URL of the album.
     */
    public String getCover() {
        return cover;
    }

    /**
     * Sets the cover URL of the album.
     *
     * @param cover The cover URL of the album.
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * Gets the title of the album.
     *
     * @return The title of the album.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the title of the album.
     *
     * @param name The title of the album.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the artist name of the album.
     *
     * @return The artist name of the album.
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * Sets the artist name of the album.
     *
     * @param artistName The artist name of the album.
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * Gets the tracklist URL of the album.
     *
     * @return The tracklist URL of the album.
     */
    public String getTracklist() {
        return tracklist;
    }

    /**
     * Sets the tracklist URL of the album.
     *
     * @param tracklist The tracklist URL of the album.
     */
    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }
}
