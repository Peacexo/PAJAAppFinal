package algonquin.cst2335.pajaappfinal;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "recipes")
public class Recipe implements Serializable {
    @PrimaryKey
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
