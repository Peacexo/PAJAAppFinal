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
public class Sunrise2_LookUpButtonTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void lookUpButtonTest() {
        ViewInteraction materialButton = onView(withId(R.id.main_sun));
        materialButton.perform(click());

        ViewInteraction appCompatEditText = onView(withId(R.id.latitudeInput));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(withId(R.id.latitudeInput));
        appCompatEditText2.perform(replaceText("48"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(withId(R.id.latitudeInput));
        appCompatEditText3.perform(click());

        ViewInteraction appCompatEditText4 = onView(withId(R.id.latitudeInput));
        appCompatEditText4.perform(replaceText("48.8566"));

        ViewInteraction appCompatEditText5 = onView(withId(R.id.latitudeInput));
        appCompatEditText5.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(withId(R.id.longitudeInput));
        appCompatEditText6.perform(replaceText("2.3522"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(withId(R.id.lookupButton));
        materialButton2.perform(click());

        ViewInteraction button = onView(withId(R.id.lookupButton));
        button.check(matches(isDisplayed()));
    }
}
