package com.example.mareu.ui.activity;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.example.mareu.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddMeetingActivityTest {

    @Rule
    public ActivityTestRule<MeetingListActivity> mActivityTestRule = new ActivityTestRule<>(MeetingListActivity.class);

    @Test
    public void addMeetingActivityTest() {
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
allOf(withId(R.id.create_meeting),
childAtPosition(
allOf(withId(R.id.activity_meeting_list),
childAtPosition(
withId(android.R.id.content),
0)),
3),
isDisplayed()));
        floatingActionButton2.perform(click());
        
        ViewInteraction appCompatEditText = onView(
allOf(withId(R.id.subject_meeting_add),
childAtPosition(
allOf(withId(R.id.activity_meeting_list),
childAtPosition(
withId(android.R.id.content),
0)),
2),
isDisplayed()));
        appCompatEditText.perform(replaceText("Esssai"), closeSoftKeyboard());
        
        ViewInteraction appCompatSpinner = onView(
allOf(withId(R.id.spinner_room),
childAtPosition(
allOf(withId(R.id.activity_meeting_list),
childAtPosition(
withId(android.R.id.content),
0)),
3),
isDisplayed()));
        appCompatSpinner.perform(click());
        
        DataInteraction appCompatCheckedTextView = onData(anything())
.inAdapterView(childAtPosition(
withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
0))
.atPosition(0);
        appCompatCheckedTextView.perform(click());
        
        ViewInteraction appCompatMultiAutoCompleteTextView = onView(
allOf(withId(R.id.tv_participants),
childAtPosition(
allOf(withId(R.id.activity_meeting_list),
childAtPosition(
withId(android.R.id.content),
0)),
4),
isDisplayed()));
        appCompatMultiAutoCompleteTextView.perform(replaceText("e"), closeSoftKeyboard());
        
        DataInteraction appCompatTextView = onData(anything())
.inAdapterView(childAtPosition(
withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
0))
.atPosition(0);
        appCompatTextView.perform(click());
        
        ViewInteraction appCompatMultiAutoCompleteTextView2 = onView(
allOf(withId(R.id.tv_participants), withText("emily@gmail.com, "),
childAtPosition(
allOf(withId(R.id.activity_meeting_list),
childAtPosition(
withId(android.R.id.content),
0)),
4),
isDisplayed()));
        appCompatMultiAutoCompleteTextView2.perform(replaceText("emily@gmail.com, m"));
        
        ViewInteraction appCompatMultiAutoCompleteTextView3 = onView(
allOf(withId(R.id.tv_participants), withText("emily@gmail.com, m"),
childAtPosition(
allOf(withId(R.id.activity_meeting_list),
childAtPosition(
withId(android.R.id.content),
0)),
4),
isDisplayed()));
        appCompatMultiAutoCompleteTextView3.perform(closeSoftKeyboard());
        
        DataInteraction appCompatTextView2 = onData(anything())
.inAdapterView(childAtPosition(
withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
0))
.atPosition(0);
        appCompatTextView2.perform(click());
        
        ViewInteraction appCompatImageButton = onView(
allOf(withId(R.id.add_meeting_button_create),
childAtPosition(
allOf(withId(R.id.activity_meeting_list),
childAtPosition(
withId(android.R.id.content),
0)),
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
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
