/**
 * DeleteAllFavoriteRecipesTest tests the functionality of deleting all favorite recipes.
 * Author: Alessandra Prunzel Kittlaus
 * Lab Section: 022
 * Creation Date: 04/04/2024
 */
package algonquin.cst2335.pajaappfinal;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DeleteAllFavoriteRecipesTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * This test clicks on the "Recipe" button in the MainActivity, then clicks on the "Favorites" button,
     * checks if the RecyclerView containing favorite recipes is displayed, clicks on the "Delete All" button,
     * confirms the deletion, and finally verifies if the "Delete All" button is still displayed after deletion.
     */
    @Test
    public void deleteAllFavoriteRecipesTest() {

        // Click on the "Recipe" button in the MainActivity
        ViewInteraction materialButton = onView(withId(R.id.main_recipe));
        materialButton.perform(click());

        // Click on the "Favorites" button in the RecipeHomeActivity
        ViewInteraction materialButton2 = onView(withId(R.id.buttonFavorites));
        materialButton2.perform(scrollTo(), click());

        // Check if the RecyclerView containing favorite recipes is displayed
        ViewInteraction recyclerView2 = onView(withId(R.id.recyclerViewFavoriteRecipes));
        recyclerView2.check(matches(isDisplayed()));

        // Click on the "Delete All" button
        ViewInteraction materialButton3 = onView(withId(R.id.buttonDeleteAll));
        materialButton3.perform(click());

        // Confirm deletion
        ViewInteraction materialButton4 = onView(withId(android.R.id.button1));
        materialButton4.perform(scrollTo(), click());

        // Verify if the "Delete All" button is still displayed after deletion
        ViewInteraction button = onView(withId(R.id.buttonDeleteAll));
        button.check(matches(isDisplayed()));

    }
}
