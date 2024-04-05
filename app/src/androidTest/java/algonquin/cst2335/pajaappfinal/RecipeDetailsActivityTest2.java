package algonquin.cst2335.pajaappfinal;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static androidx.test.espresso.matcher.ViewMatchers.withText;


import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecipeDetailsActivityTest2 {

    @Rule
    public ActivityScenarioRule<RecipeHomeActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(RecipeHomeActivity.class);

    @Test
    public void recipeDetailsActivityTest2() {
        ViewInteraction appCompatEditText = onView(withId(R.id.editSearchRecipe));
        appCompatEditText.perform(replaceText("burger"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(withId(R.id.searchRecipeButton));
        materialButton.perform(scrollTo(), click());

        ViewInteraction recyclerView = onView(withId(R.id.recycler_view_recipes));
        recyclerView.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction textView = onView(withId(R.id.textViewSummary));
        textView.check(matches(withText("The recipe Falafel Burger is ready <b>in roughly 45 minutes</b> and is definitely an outstanding <b>dairy free and lacto ovo vegetarian</b> option for lovers of middl eastern food. This recipe serves 4 and costs $1.64 per serving. One portion of this dish contains approximately <b>11g of protein</b>, <b>23g of fat</b>, and a total of <b>400 calories</b>. It works well as a hor d'oeuvre. 4 people were impressed by this recipe. If you have chickpeas, onion, parsley, and a few other ingredients on hand, you can make it. It is brought to you by Foodista. Overall, this recipe earns a <b>tremendous spoonacular score of 82%</b>. <a href=\"https://spoonacular.com/recipes/falafel-burger-1371717\">Falafel Burger</a>, <a href=\"https://spoonacular.com/recipes/falafel-burger-1220097\">Falafel Burger</a>, and <a href=\"https://spoonacular.com/recipes/falafel-burger-1395111\">Falafel Burger</a> are very similar to this recipe.")));
    }

}
