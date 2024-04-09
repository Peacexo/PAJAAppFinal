/**
 * DeleteAllFavoriteUndoFunctionTest tests the functionality of deleting all favorite recipes with undo option.
 * Author: Alessandra Prunzel Kittlaus
 * Lab Section: 022
 * Creation Date: 04/04/2024
 */
package algonquin.cst2335.pajaappfinal;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
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
public class DeleteAllFavoriteUndoFunctionTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * This test clicks on the "Recipe" button in the MainActivity, then clicks on the "Favorites" button,
     * then clicks on the "Delete All" button, confirms the deletion, and finally clicks on the undo action
     * in the snackbar to undo the deletion.
     */
    @Test
    public void deleteAllFavoriteUndoFunctionTest() {

        // Click on the "Recipe" button in the MainActivity
        ViewInteraction materialButton = onView(withId(R.id.main_recipe));
        materialButton.perform(click());

        // Click on the "Favorites" button in the RecipeHomeActivity
        ViewInteraction materialButton4 = onView(withId(R.id.buttonFavorites));
        materialButton4.perform(scrollTo(), click());

        // Click on the "Delete All" button
        ViewInteraction materialButton5 = onView(withId(R.id.buttonDeleteAll));
        materialButton5.perform(click());

        // Confirm deletion
        ViewInteraction materialButton6 = onView(withId(android.R.id.button1));
        materialButton6.perform(scrollTo(), click());

        // Click on the undo action in the snackbar to undo the deletion
        ViewInteraction materialButton7 = onView(withId(com.google.android.material.R.id.snackbar_action));
        materialButton7.perform(click());

    }
}
