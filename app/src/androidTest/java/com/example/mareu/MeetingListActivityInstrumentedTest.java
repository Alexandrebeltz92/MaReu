package com.example.mareu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.ui.activity.MeetingListActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingListActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<MeetingListActivity> mActivityRule = new ActivityTestRule<>(MeetingListActivity.class);

    /**
     * We ensure that our recyclerview is displaying zero item when we launch the app
     */

    @Test
    public void maReuListIsEmptyAtFirst() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.activity_meeting_list)).check(matches(hasMinimumChildCount(0)));
    }

    /**
     * We ensure that the button add meeting create a random meeting
     */

    @Test
    public void addMeetingRandomWithSuccess() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_meeting), withContentDescription("Add meeting button"),
                        childAtPosition(
                                allOf(withId(R.id.activity_meeting_list),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.add_random_meeting),
                        childAtPosition(
                                allOf(withId(R.id.activity_meeting_list),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        floatingActionButton2.perform(click());
    }

    /**
     * We ensure that we open the add activity when we click on the button
     */
    @Test
    public void openAddMeetingActivityWithSuccess() {
    ViewInteraction floatingActionButton = onView(
            allOf(withId(R.id.add_meeting), withContentDescription("Add meeting button"),
                    childAtPosition(
                            allOf(withId(R.id.activity_meeting_list),
                                    childAtPosition(
                                            withId(android.R.id.content),
                                            0)),
                            1),
                    isDisplayed()));
        floatingActionButton.perform(click());

    ViewInteraction floatingActionButton3 = onView(
            allOf(withId(R.id.create_meeting),
                    childAtPosition(
                            allOf(withId(R.id.activity_meeting_list),
                                    childAtPosition(
                                            withId(android.R.id.content),
                                            0)),
                            3),
                    isDisplayed()));
        floatingActionButton3.perform(click());
}

    /**
     * We ensure that we can delete a meeting when we click on the delete button
     */

    @Test
    public void deleteMeetingWithSuccess() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_meeting), withContentDescription("Add meeting button"),
                        childAtPosition(
                                allOf(withId(R.id.activity_meeting_list),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.add_random_meeting),
                        childAtPosition(
                                allOf(withId(R.id.activity_meeting_list),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.item_list_delete_button), withContentDescription("Delete Button"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                7),
                        isDisplayed()));
        appCompatImageButton.perform(click());

    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}