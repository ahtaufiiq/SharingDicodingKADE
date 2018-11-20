package com.example.ahmad.footbalmatch

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.ahmad.footbalmatch.R.id.*
import com.example.ahmad.footbalmatch.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {


    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {

        //Last Match
        sleep_long()

        onView(withId(rv_match))
                .check(matches(isDisplayed()))
        onView(withId(rv_match))
                .perform(scrollToPosition<RecyclerView.ViewHolder>(14))
        onView(withId(rv_match))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(14,
                        click()))
        sleep_short()

        onView(withId(img_team_away))
                .check(matches(isDisplayed()))
        onView(withId(img_team_home))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite))
                .perform(click())
        pressBack()

        sleep_long()

        //Next Match
        onView(withId(navigation_next_match))
                .perform(click())

        sleep_long()

        onView(withId(rv_match))
                .check(matches(isDisplayed()))
        onView(withId(rv_match))
                .perform(scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(rv_match))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(5,
                        click()))
        sleep_short()
        onView(withId(img_team_away))
                .check(matches(isDisplayed()))
        onView(withId(img_team_home))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite))
                .perform(click())
        pressBack()

        sleep_long()
        //Favorite
        onView(withId(navigation_favorite))
                .perform(click())

        sleep_long()

        onView(withId(rv_favorite))
                .check(matches(isDisplayed()))
        onView(withId(rv_favorite)).perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                        click()))
        sleep_short()
        onView(withId(img_team_away))
                .check(matches(isDisplayed()))
        onView(withId(img_team_home))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite))
                .perform(click())
        pressBack()

        sleep_long()

    }

    private fun sleep_long() {
        Thread.sleep(3000)
    }
    private fun sleep_short() {
        Thread.sleep(1000)
    }
}