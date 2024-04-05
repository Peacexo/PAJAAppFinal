package algonquin.cst2335.pajaappfinal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.math.BigInteger;

/**
 * Represents an artist retrieved from the Deezer Song Search API.
 * Each artist has an ID, name, poster URL, and tracklist URL.
 *
 * <p>
 * -------------------------------------------------------
 * Course: CST 2335 - Mobile Graphical Interface Programming
 * Final Project: Deezer Song Search API
 * Student Name: Allan Torres
 * Student Number: 041022473
 * -------------------------------------------------------
 * </p>
 */
@Entity
public class Artist {
    /**
     * The unique identifier for the artist.
     */
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    /**
     * The name of the artist.
     */
    @ColumnInfo(name = "name")
    private String name;

    /**
     * The URL of the artist's poster image.
     */
    @ColumnInfo(name = "poster")
    private String poster;

    /**
     * The URL of the tracklist for the artist.
     */
    private String tracklist;

    /**
     * Constructs a new Artist object with the specified details.
     *
     * @param name     The name of the artist.
     * @param poster   The URL of the artist's poster image.
     * @param tracklist The URL of the tracklist for the artist.
     */
    public Artist(String name, String poster, String tracklist) {
        this.name = name;
        this.poster = poster;
        this.tracklist = tracklist;
    }

    /**
     * Gets the ID of the artist.
     *
     * @return The ID of the artist.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the artist.
     *
     * @param id The ID of the artist.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the artist.
     *
     * @return The name of the artist.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the artist.
     *
     * @param name The name of the artist.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the URL of the artist's poster image.
     *
     * @return The URL of the artist's poster image.
     */
    public String getPoster() {
        return poster;
    }

    /**
     * Gets the URL of the tracklist for the artist.
     *
     * @return The URL of the tracklist for the artist.
     */
    public String getTracklist() {
        return tracklist;
    }

    /**
     * Sets the URL of the tracklist for the artist.
     *
     * @param tracklist The URL of the tracklist for the artist.
     */
    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    /**
     * Sets the URL of the artist's poster image.
     *
     * @param poster The URL of the artist's poster image.
     */
    public void setPoster(String poster) {
        this.poster = poster;
    }
}
