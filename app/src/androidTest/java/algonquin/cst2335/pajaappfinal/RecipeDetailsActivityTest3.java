package algonquin.cst2335.pajaappfinal;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecipeDetailsActivityTest3 {

    @Rule
    public ActivityScenarioRule<RecipeHomeActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(RecipeHomeActivity.class);

    @Test
    public void recipeDetailsActivityTest3() {
        onView(withId(R.id.editSearchRecipe))
                .perform(replaceText("pizza"), closeSoftKeyboard());

        onView(withId(R.id.searchRecipeButton))
                .perform(scrollTo(), click());

        onView(withId(R.id.recycler_view_recipes))
                .perform(actionOnItemAtPosition(6, click()));

        onView(withId(R.id.buttonSaveToFavorites))
                .perform(scrollTo(), click());

        onView(withId(R.id.buttonSaveToFavorites))
                .check(matches(isDisplayed()));
    }
}

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        try {
//            Thread.sleep(5365);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        ViewInteraction appCompatEditText = onView(
//                allOf(withId(R.id.editSearchRecipe),
//                        childAtPosition(
//                                childAtPosition(
//                                        withClassName(is("android.widget.ScrollView")),
//                                        0),
//                                3)));
//        appCompatEditText.perform(scrollTo(), click());
//
//        ViewInteraction appCompatEditText2 = onView(
//                allOf(withId(R.id.editSearchRecipe),
//                        childAtPosition(
//                                childAtPosition(
//                                        withClassName(is("android.widget.ScrollView")),
//                                        0),
//                                3)));
//        appCompatEditText2.perform(scrollTo(), replaceText("pizza"), closeSoftKeyboard());
//
//        pressBack();
//
//        ViewInteraction materialButton = onView(
//                allOf(withId(R.id.searchRecipeButton), withText("Search"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withClassName(is("android.widget.ScrollView")),
//                                        0),
//                                4)));
//        materialButton.perform(scrollTo(), click());
//
//        ViewInteraction recyclerView = onView(
//                allOf(withId(R.id.recycler_view_recipes),
//                        childAtPosition(
//                                withClassName(is("android.widget.RelativeLayout")),
//                                6)));
//        recyclerView.perform(actionOnItemAtPosition(6, click()));
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        try {
//            Thread.sleep(700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        ViewInteraction materialButton2 = onView(
//                allOf(withId(R.id.buttonSaveToFavorites), withText("Save to Favorites"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withClassName(is("android.widget.ScrollView")),
//                                        0),
//                                4)));
//        materialButton2.perform(scrollTo(), click());
//
//        ViewInteraction button = onView(
//                allOf(withId(R.id.buttonSaveToFavorites), withText("Save to Favorites"),
//                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
//                        isDisplayed()));
//        button.check(matches(isDisplayed()));
//
//        ViewInteraction button2 = onView(
//                allOf(withId(R.id.buttonSaveToFavorites), withText("Save to Favorites"),
//                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
//                        isDisplayed()));
//        button2.check(matches(isDisplayed()));
//    }
//
//    private static Matcher<View> childAtPosition(
//            final Matcher<View> parentMatcher, final int position) {
//
//        return new TypeSafeMatcher<View>() {
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("Child at position " + position + " in parent ");
//                parentMatcher.describeTo(description);
//            }
//
//            @Override
//            public boolean matchesSafely(View view) {
//                ViewParent parent = view.getParent();
//                return parent instanceof ViewGroup && parentMatcher.matches(parent)
//                        && view.equals(((ViewGroup) parent).getChildAt(position));
//            }
//        };
//    }
//}
