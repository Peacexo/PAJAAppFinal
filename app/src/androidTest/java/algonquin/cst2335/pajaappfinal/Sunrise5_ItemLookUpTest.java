package algonquin.cst2335.pajaappfinal;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Sunrise5_ItemLookUpTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void sunrise5_ItemLookUpTest() {
        ViewInteraction materialButton = onView(withId(R.id.main_sun));
        materialButton.perform(click());

//        ViewInteraction materialButton2 = onView(withId(R.id.view_favorites_button));
//        materialButton2.perform(click());

        ViewInteraction appCompatEditText = onView(withId(R.id.latitudeInput));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(withId(R.id.latitudeInput));
        appCompatEditText2.perform(replaceText("40.7128"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(withId(R.id.longitudeInput));
        appCompatEditText3.perform(replaceText("-74.0060"), closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(withId(R.id.lookupButton));
        materialButton3.perform(click());

//        ViewInteraction materialButton4 = onView(withId(R.id.addFavoriteButton));
//        materialButton4.perform(click());
//
//        ViewInteraction materialButton5 = onView(withId(R.id.addFavoriteButton));
//        materialButton5.perform(click());

//        ViewInteraction materialButton6 = onView(withId(R.id.backButton));
//        materialButton6.perform(click());

//        ViewInteraction materialButton7 = onView(withId(R.id.view_favorites_button));
//        materialButton7.perform(click());
//
//        ViewInteraction materialButton8 = onView(withId(R.id.button_todays_timings));
//        materialButton8.perform(click());

//        ViewInteraction button = onView(withId(R.id.backButton));
//        button.check(matches(isDisplayed()));
    }

}
