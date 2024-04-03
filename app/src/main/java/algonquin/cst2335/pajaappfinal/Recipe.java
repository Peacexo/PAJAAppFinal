package algonquin.cst2335.pajaappfinal;

public class Recipe {
    private int id;
    private String title;
    private String imageUrl;

    private String summary;
    private String fullRecipeUrl;

    public Recipe(int id, String title, String imageUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public Recipe(int id, String title, String imageUrl, String summary, String fullRecipeUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.summary = summary;
        this.fullRecipeUrl = fullRecipeUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getFullRecipeUrl() {
        return fullRecipeUrl;
    }

    public void setFullRecipeUrl(String fullRecipeUrl) {
        this.fullRecipeUrl = fullRecipeUrl;
    }
}
