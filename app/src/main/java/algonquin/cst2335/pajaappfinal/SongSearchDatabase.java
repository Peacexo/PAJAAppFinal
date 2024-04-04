package algonquin.cst2335.pajaappfinal;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Artist.class, Album.class, Song.class}, version=1)
public abstract class SongSearchDatabase extends RoomDatabase {

    public abstract ArtistDAO artistDAO();
    public abstract AlbumDAO albumDAO();
    public abstract SongDAO songDAO();
}
