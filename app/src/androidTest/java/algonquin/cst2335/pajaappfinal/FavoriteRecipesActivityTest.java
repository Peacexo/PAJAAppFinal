/**
 * FavoriteRecipesActivityTest tests the functionality of displaying favorite recipes in the FavoriteRecipesActivity.
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
public class FavoriteRecipesActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * This test clicks on the "Recipe" button in the MainActivity, then clicks on the "Favorites" button,
     * and finally verifies that the RecyclerView displaying favorite recipes is displayed.
     */
    @Test
    public void favoriteRecipesActivityTest() {
        // Click on the "Recipe" button in the MainActivity
        ViewInteraction materialButton = onView(withId(R.id.Recipe));
        materialButton.perform(click());

        // Click on the "Favorites" button in the RecipeHomeActivity
        ViewInteraction materialButton2 = onView(withId(R.id.buttonFavorites));
        materialButton2.perform(scrollTo(), click());

        // Verify that the RecyclerView displaying favorite recipes is displayed
        ViewInteraction recyclerView2 = onView(withId(R.id.recyclerViewFavoriteRecipes));
        recyclerView2.check(matches(isDisplayed()));
    }

}