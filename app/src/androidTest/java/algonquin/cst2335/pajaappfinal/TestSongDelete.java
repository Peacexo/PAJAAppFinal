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
public class TestSongDelete {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testSongDelete() {

        //Must have at least one song in the favorite list

        ViewInteraction materialButton = onView(withId(R.id.main_deezer));
        materialButton.perform(click());

        ViewInteraction actionMenuItemView = onView(withId(R.id.song_favorite_item));

        actionMenuItemView.perform(click());

        ViewInteraction materialButton2 = onView(withId(R.id.delete_bttn));
        materialButton2.perform(click());

//        ViewInteraction materialButton3 = onView(withId(android.R.id.button1));
//        materialButton3.perform(scrollTo(), click());

//        ViewInteraction recyclerView = onView(withId(R.id.favorites_list));
//        recyclerView.check(matches(isDisplayed()));
//
//        ViewInteraction recyclerView2 = onView(withId(R.id.favorites_list));
//        recyclerView2.check(matches(isDisplayed()));
    }


}
