/**
 * MenuItemTest tests the functionality of menu items in the RecipeHomeActivity.
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
public class MenuItemTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * This test clicks on the "Recipe" button, then clicks on the "About" menu item,
     * and finally verifies that the "About" button is displayed.
     */
    @Test
    public void menuItemTest() {
        // Click on the "Recipe" button in the MainActivity
        ViewInteraction materialButton = onView(withId(R.id.main_recipe));
        materialButton.perform(click());

        // Click on the "About" menu item in the RecipeHomeActivity
        ViewInteraction actionMenuItemView = onView(withId(R.id.itemAbout));
        actionMenuItemView.perform(scrollTo(), click());

        // Click on the "Got it" button in the dialog
        ViewInteraction materialButton2 = onView(withId(android.R.id.button1));
        materialButton2.perform(scrollTo(), click());

        // Verify that the "About" button is displayed
        ViewInteraction button = onView(withId(R.id.itemAbout));
        button.check(matches(isDisplayed()));
    }

}