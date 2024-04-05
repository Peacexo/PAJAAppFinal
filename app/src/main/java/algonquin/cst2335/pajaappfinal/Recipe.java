/**
 * Recipe represents a single recipe item, containing its ID, title, image URL, summary, and full recipe URL.
 * Author: Alessandra Prunzel Kittlaus
 * Lab Section: 022
 * Creation Date: 04/04/2024
 */
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

    /**
     * Constructor to create a new Recipe object.
     * @param id The ID of the recipe.
     * @param title The title of the recipe.
     * @param imageUrl The URL of the image associated with the recipe.
     */
    public Recipe(int id, String title, String imageUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    /**
     * Getters and setters for the Recipe attributes
     */

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
