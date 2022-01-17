package com.example.android_tip_app

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TipAppTest {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun calculate_20() {
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("50.00"))
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.result)).check(matches(withText(containsString("10.00"))))
    }
    @Test
    fun calculate_15() {
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("50.00"))
        onView(withId(R.id.option_fifteen)).perform(click())
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.result)).check(matches(withText(containsString("8.00"))))
    }
    @Test
    fun calculate_15_without_round() {
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("50.00"))
        onView(withId(R.id.option_fifteen)).perform(click())
        onView(withId(R.id.round_switch)).perform(click())
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.result)).check(matches(withText(containsString("7.50"))))
    }
}