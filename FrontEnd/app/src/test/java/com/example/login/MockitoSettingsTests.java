package com.example.login;

import android.widget.Button;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.example.login.Settings.data;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MockitoSettingsTests {
    @Rule
    public ActivityScenarioRule<Settings> activityRule = new ActivityScenarioRule<Settings>(Settings.class);
    @Rule
    public ActivityTestRule<Settings> rule = new ActivityTestRule<>(Settings.class);

    @Test
    public void ensureButtonsIsPresent() throws Exception {
        Settings activity = rule.getActivity();
        Button btn = activity.findViewById(R.id.btnLogout);
        assertThat(btn, notNullValue());
        assertThat(btn, instanceOf(Button.class));
    }

    @Test
    public void changeSmokingFilter() {
        onView(withId(R.id.button5)).perform(click());
        assertFalse(data[0]);
    }

    @Test
    public void changeEducationFilter() {
        onView(withId(R.id.button6)).perform(click());
        assertFalse(data[1]);
    }

    @Test
    public void changeAnotherFilter() {
        onView(withId(R.id.button7)).perform(click());
        assertFalse(data[2]);
    }

    @Test
    public void ensureEventStarted() {
        onView(withId(R.id.btnLogout)).perform(click());
    }
}
