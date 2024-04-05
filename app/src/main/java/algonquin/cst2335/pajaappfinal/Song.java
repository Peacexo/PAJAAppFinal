package algonquin.cst2335.pajaappfinal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Represents a song retrieved from the Deezer Song Search API.
 * Each song has an ID, title, track position, duration, artist name, album cover URL,
 * album title, tracklist URL, and album ID.
 */
@Entity
public class Song {
    /**
     * The unique identifier for the song.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    /**
     * The title of the song.
     */
    @ColumnInfo(name = "title")
    private String title;

    /**
     * The position of the song in its album's tracklist.
     */
    @ColumnInfo(name = "track_position")
    private int trackPosition;

    /**
     * The duration of the song in seconds.
     */
    @ColumnInfo(name = "duration")
    private int duration;

    /**
     * The name of the artist who created the song.
     */
    @ColumnInfo(name = "artist_name")
    private String artistName;

    /**
     * The URL of the album cover image for the song.
     */
    @ColumnInfo(name = "album_cover")
    private String cover;

    /**
     * The title of the album that contains the song.
     */
    @ColumnInfo(name = "album_title")
    private String albumTitle;

    /**
     * The URL of the tracklist for the song's album.
     */
    @ColumnInfo(name = "tracklist")
    private String tracklist;

    /**
     * The ID of the album that contains the song.
     */
    @ColumnInfo(name = "album_id")
    private int albumID;

    /**
     * Constructs a new Song object.
     */
    public Song() {
    }

    /**
     * Constructs a new Song object with the specified details.
     *
     * @param title         The title of the song.
     * @param duration      The duration of the song in seconds.
     * @param trackPosition The position of the song in its album's tracklist.
     * @param artistName    The name of the artist who created the song.
     * @param cover         The URL of the album cover image for the song.
     * @param albumTitle    The title of the album that contains the song.
     * @param tracklist     The URL of the tracklist for the song's album.
     */
    @Ignore
    public Song(String title, int duration, int trackPosition, String artistName, String cover, String albumTitle, String tracklist) {
        this.title = title;
        this.trackPosition = trackPosition;
        this.duration = duration;
        this.artistName = artistName;
        this.cover = cover;
        this.albumTitle = albumTitle;
        this.tracklist = tracklist;
    }

    /**
     * Gets the ID of the song.
     *
     * @return The ID of the song.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the song.
     *
     * @param id The ID of the song.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the title of the song.
     *
     * @return The title of the song.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the song.
     *
     * @param title The title of the song.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the track position of the song.
     *
     * @return The track position of the song.
     */
    public int getTrackPosition() {
        return trackPosition;
    }

    /**
     * Sets the track position of the song.
     *
     * @param trackPosition The track position of the song.
     */
    public void setTrackPosition(int trackPosition) {
        this.trackPosition = trackPosition;
    }

    /**
     * Gets the duration of the song in seconds.
     *
     * @return The duration of the song.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the song in seconds.
     *
     * @param duration The duration of the song.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets the artist name of the song.
     *
     * @return The artist name of the song.
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * Sets the artist name of the song.
     *
     * @param artistName The artist name of the song.
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * Gets the album cover URL of the song.
     *
     * @return The album cover URL of the song.
     */
    public String getCover() {
        return cover;
    }

    /**
     * Sets the album cover URL of the song.
     *
     * @param cover The album cover URL of the song.
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * Gets the album title of the song.
     *
     * @return The album title of the song.
     */
    public String getAlbumTitle() {
        return albumTitle;
    }

    /**
     * Sets the album title of the song.
     *
     * @param albumTitle The album title of the song.
     */
    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    /**
     * Gets the tracklist URL of the song's album.
     *
     * @return The tracklist URL of the song's album.
     */
    public String getTracklist() {
        return tracklist;
    }

    /**
     * Sets the tracklist URL of the song's album.
     *
     * @param tracklist The tracklist URL of the song's album.
     */
    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    /**
     * Gets the album ID of the song.
     *
     * @return The album ID of the song.
     */
    public int getAlbumID() {
        return albumID;
    }

    /**
     * Sets the album ID of the song.
     *
     * @param albumID The album ID of the song.
     */
    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }
}
