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
public class Test_Search_Button {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void test_Search_Button() {


        ViewInteraction materialButton = onView(withId(R.id.Deezer));
        materialButton.perform(click());



        ViewInteraction appCompatEditText = onView(withId(R.id.search_editText));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(withId(R.id.search_editText));
        appCompatEditText2.perform(replaceText("pitty"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(withId(R.id.search_button));
        materialButton2.perform(click());

        ViewInteraction button = onView(withId(R.id.search_button));
        button.check(matches(isDisplayed()));
    }
}
