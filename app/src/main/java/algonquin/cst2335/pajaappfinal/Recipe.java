package algonquin.cst2335.pajaappfinal;

public class Recipe {

    private int recipeId;
    private String recipeTitle;
    private String recipeImageUrl;

    public Recipe(int recipeId, String recipeTitle, String recipeImageUrl) {
        this.recipeId = recipeId;
        this.recipeTitle = recipeTitle;
        this.recipeImageUrl = recipeImageUrl;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getRecipeImageUrl() {
        return recipeImageUrl;
    }

    public void setRecipeImageUrl(String recipeImageUrl) {
        this.recipeImageUrl = recipeImageUrl;
    }

}