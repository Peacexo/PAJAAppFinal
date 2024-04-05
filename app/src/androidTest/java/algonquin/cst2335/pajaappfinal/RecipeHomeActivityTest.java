/**
 * RecipeHomeActivityTest tests the functionality of the RecipeHomeActivity.
 * This test performs a search for a recipe and verifies that the search button is displayed.
 * Author: Alessandra Prunzel Kittlaus
 * Lab Section: 022
 * Creation Date: 04/04/2024
 */
package algonquin.cst2335.pajaappfinal;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
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
public class RecipeHomeActivityTest {

    @Rule
    public ActivityScenarioRule<RecipeHomeActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(RecipeHomeActivity.class);

    /**
     * This test performs a search for a recipe and verifies that the search button is displayed.
     */
    @Test
    public void recipeHomeActivityTest() {

        // Find and interact with the search edit text field
        ViewInteraction appCompatEditText = onView(withId(R.id.editSearchRecipe));
        // Replace the text in the search field with "burger"
        appCompatEditText.perform(replaceText("burger"), closeSoftKeyboard());

        // Find and click on the search button
        ViewInteraction materialButton = onView(withId(R.id.searchRecipeButton));
        materialButton.perform(click());

        // Check if the search button is displayed
        ViewInteraction button = onView(withId(R.id.searchRecipeButton));
        button.check(matches(isDisplayed()));
    }
}