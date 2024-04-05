package algonquin.cst2335.pajaappfinal;


import static androidx.test.espresso.Espresso.onView;

import static androidx.test.espresso.action.ViewActions.click;

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
public class Test_Favorites {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void test_Favorites() {


        ViewInteraction materialButton = onView(withId(R.id.Deezer));
        materialButton.perform(click());

        ViewInteraction actionMenuItemView = onView(withId(R.id.song_favorite_item));
        actionMenuItemView.perform(click());


        ViewInteraction viewGroup = onView(withId(R.id.favorites_list));
        viewGroup.check(matches(isDisplayed()));
    }



}
