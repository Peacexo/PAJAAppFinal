package algonquin.cst2335.pajaappfinal;
public class Artist {

    private String name, poster, tracklist;
    public Artist(String name, String poster, String tracklist){
        this.name = name;
        this.poster = poster;
        this.tracklist = tracklist;

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
