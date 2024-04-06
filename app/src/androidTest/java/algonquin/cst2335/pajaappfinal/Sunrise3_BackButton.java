package algonquin.cst2335.pajaappfinal;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class Sunrise3_BackButton {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void backBottun() {
        // You can access the activity scenario using getScenario() method
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            // Perform actions on the activity if needed
        });

        ViewInteraction materialButton = Espresso.onView(ViewMatchers.withId(R.id.SunSeeker));
        materialButton.perform(ViewActions.click());

        ViewInteraction appCompatEditText = Espresso.onView(ViewMatchers.withId(R.id.latitudeInput));
        appCompatEditText.perform(ViewActions.replaceText("48.8566"), ViewActions.closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = Espresso.onView(ViewMatchers.withId(R.id.longitudeInput));
        appCompatEditText2.perform(ViewActions.replaceText("2.3522"), ViewActions.closeSoftKeyboard());

        ViewInteraction materialButton3 = Espresso.onView(ViewMatchers.withId(R.id.lookupButton));
        materialButton3.perform(ViewActions.click());

//        ViewInteraction materialButton4 = Espresso.onView(ViewMatchers.withId(R.id.backButton));
//        materialButton4.perform(ViewActions.click());
    }
}

