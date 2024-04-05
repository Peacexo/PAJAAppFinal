package algonquin.cst2335.pajaappfinal;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

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
public class BackButton {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void backBottun() {
        ViewInteraction materialButton = onView(withId(R.id.SunSeeker));
        materialButton.perform(click());

        ViewInteraction appCompatEditText = onView(withId(R.id.latitudeInput));
        appCompatEditText.perform(replaceText("48.8566"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(withId(R.id.longitudeInput));
        appCompatEditText2.perform(replaceText("2.3522"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(withId(R.id.lookupButton));
        materialButton2.perform(click());

        ViewInteraction materialButton3 = onView(withId(R.id.backButton));
        materialButton3.perform(click());

        ViewInteraction appCompatEditText3 = onView(withId(R.id.latitudeInput));
        appCompatEditText3.perform(replaceText("48.8566"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(withId(R.id.longitudeInput));
        appCompatEditText4.perform(replaceText("2.3522"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(withId(R.id.lookupButton));
        materialButton4.perform(click());

        ViewInteraction button = onView(withId(R.id.backButton));
        button.check(matches(isDisplayed()));
    }


}
