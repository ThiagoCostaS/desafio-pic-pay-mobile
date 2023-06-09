package com.thiagoc.desafiopicpay.presentation.utils

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isSystemAlertWindow
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.not


fun checkToastIsDisplayed(text: String) {
    onView(withText(text))
        .inRoot(isSystemAlertWindow())
        .check(matches(isDisplayed()))
    onView(withText(text))
        .inRoot(isSystemAlertWindow())
        .check(matches(not(isDisplayed())))
}