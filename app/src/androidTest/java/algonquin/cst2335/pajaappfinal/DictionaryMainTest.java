package algonquin.cst2335.pajaappfinal;


import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;import androidx.test.filters.LargeTest;

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
    public ActivityScenarioRule<MainActivity> activityTestRule =
            new ActivityScenarioRule<>(MainActivity.class);

    private SharedPreferences sharedPreferences;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        sharedPreferences = context.getSharedPreferences("SearchPrefs", Context.MODE_PRIVATE);
    }

    @After
    public void tearDown() {
        sharedPreferences.edit().clear().apply();
    }

    @Test
    public void testNoSearchTermEntered() {
        onView(withId(R.id.searchButton)).perform(click());
        // Assert that no network request is made
        // (implementation dependent on how network requests are handled)
    }

    @Test
    public void testHelpMenuFunctionality() {
        onView(withId(R.id.help_dic)).perform(click());
        // Assert that the help dialog is displayed with instructions
        onView(withText("Help")).check(matches(isDisplayed()));
        onView(withText("Instructions for using the interface:")).check(matches(isDisplayed()));
    }


}