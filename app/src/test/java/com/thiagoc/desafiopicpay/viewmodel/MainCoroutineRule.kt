package com.thiagoc.desafiopicpay.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class MainCoroutineRule(
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher() {

    override fun starting(description: Description?) {
        if (description != null) {
            super.starting(description)
        }
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        if (description != null) {
            super.finished(description)
        }
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}