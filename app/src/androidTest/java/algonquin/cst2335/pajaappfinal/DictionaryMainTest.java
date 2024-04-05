package algonquin.cst2335.pajaappfinal;



import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.*;


import androidx.test.filters.LargeTest;



@LargeTest
@RunWith(AndroidJUnit4.class)
//public class DictionaryMainTest {
//
//    @Rule
//    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
//            new ActivityScenarioRule<>(MainActivity.class);
//
//    @Test
//    public void dictionaryMainTest() {
//    }
//}
public class DictionaryMainTest {

    @Rule
    public ActivityScenarioRule<DictionaryMain> activityTestRule =
            new ActivityScenarioRule<>(DictionaryMain.class);


    @Test
    public void testNoSearchTermEntered() {
        // Click on the search button without entering any search term
        onView(withId(R.id.searchButton)).perform(ViewActions.click());

        // Add assertions here to verify the behavior when no search term is entered
    }


    @Test
    public void testHelpMenuFunctionality() {
        // Launch the activity
        ActivityScenario<DictionaryMain> activityScenario = ActivityScenario.launch(DictionaryMain.class);

        // Perform click on the help menu item
        Espresso.onView(ViewMatchers.withId(R.id.help_dic)).perform(ViewActions.click());

        // Check if the dialog is displayed
        Espresso.onView(withText(R.string.help_title_dic)).check(matches(isDisplayed()));
        Espresso.onView(withText(R.string.help_message_dic)).check(matches(isDisplayed()));
    }

    @Test
    public void testSearchEditTextVisible() {
        // Check if searchEditText is visible
        Espresso.onView(ViewMatchers.withId(R.id.searchEditText))
                .check(matches(isDisplayed()));
    }

//@Test
//public void testInvalidSearchTerm() {
//    // Type gibberish in the search field and perform search
//    onView(withId(R.id.searchEditText))
//            .perform(typeText("12345"), closeSoftKeyboard());
//    onView(withId(R.id.searchButton)).perform(click());
//
//    // Check if a Snackbar with error message is displayed
//    onView(withText(containsString("Error"))).check(matches(isDisplayed()));
//}
    @Test
    public void testSearchButtonVisible() {
        // Check if search button is visible
        Espresso.onView(ViewMatchers.withId(R.id.searchButton))
                .check(matches(isDisplayed()));
    }
    @Test
    public void testSearchButtonClickable() {
        // Check if search button is clickable
        onView(withId(R.id.searchButton)).check(matches(isClickable())).perform(click());
    }
}