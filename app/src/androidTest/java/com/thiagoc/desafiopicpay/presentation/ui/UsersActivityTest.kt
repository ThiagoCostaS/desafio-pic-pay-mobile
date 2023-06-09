package com.thiagoc.desafiopicpay.presentation.ui

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.thiagoc.desafiopicpay.R
import com.thiagoc.desafiopicpay.RecyclerViewMatchers.shouldHaveTextAtPosition
import com.thiagoc.desafiopicpay.presentation.utils.checkToastIsDisplayed
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Test

class UsersActivityTest {

    private val server = MockWebServer()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun shouldDisplayTitle() {
        launchActivity<UsersActivity>().apply {
            val expectedTitle = context.getString(R.string.title)

            moveToState(Lifecycle.State.RESUMED)

            Espresso.onView(ViewMatchers.withText(expectedTitle))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    @Test
    fun shouldDisplayListItem() {
        server.start(serverPort)
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/users" -> successResponse
                    else -> errorResponse
                }
            }
        }

        launchActivity<UsersActivity>().apply {
            moveToState(Lifecycle.State.RESUMED)
            with(R.id.recyclerView) {
                shouldHaveTextAtPosition("Eduardo Santos", 0)
            }
        }

        server.close()
    }


    @Test
    fun shouldDisplayToastError() {
        server.start(serverPort)
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/users" -> errorResponse
                    else -> successResponse
                }
            }
        }

        launchActivity<UsersActivity>().apply {

            checkToastIsDisplayed(errorResponse.status)
        }

        server.close()
    }
    companion object {

        private const val serverPort = 9000

        private val successResponse by lazy {
            val body =
                "[{\"id\":1001,\"name\":\"Eduardo Santos\",\"img\":\"https://randomuser.me/api/portraits/men/9.jpg\",\"username\":\"@eduardo.santos\"}]"

            MockResponse()
                .setResponseCode(200)
                .setBody(body)
                .addHeader("Content-Type", "application/json")
        }

        private val errorResponse by lazy { MockResponse().setResponseCode(404) }
    }
}