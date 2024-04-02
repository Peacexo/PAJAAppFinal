package algonquin.cst2335.pajaappfinal;
public class Song {
    private String title;
    private int trackPosition, duration;

    public Song(String title, int trackPosition ,int duration) {
        this.title = title;
        this.trackPosition = trackPosition;
        this.duration = duration;
    }

    public int getTrackPosition() {
        return trackPosition;
    }

    public void setTrackPosition(int trackPosition) {
        this.trackPosition = trackPosition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
