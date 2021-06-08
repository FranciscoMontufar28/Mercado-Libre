package com.francisco.meliclone.util

import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers

fun Int.verifyText(text: String): ViewInteraction =
    Espresso.onView(ViewMatchers.withId(this)).check(
        ViewAssertions.matches(
            ViewMatchers.withText(
                text
            )
        )
    )

fun Int.clickOnRecyclerAtPosition(position: Int) {
    Espresso.onView(ViewMatchers.withId(this)).perform(
        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            position,
            ViewActions.click()
        )
    )
}

fun Int.writeInSearchView(text: String) {
    Espresso.onView(ViewMatchers.isAssignableFrom(AutoCompleteTextView::class.java)).perform(
        ViewActions.typeText(text)
    )
}

fun Int.click(): ViewInteraction =
    Espresso.onView(ViewMatchers.withId(this)).perform(ViewActions.click())

fun Int.verifyIsDisplayed(): ViewInteraction =
    Espresso.onView(ViewMatchers.withId(this))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

fun pressBackButton() {
    Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
}