package com.cristian.pruebayape

import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.cristian.pruebayape.ui.views.MainActivity
import okhttp3.mockwebserver.SocketPolicy
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.net.HttpURLConnection
import androidx.test.espresso.Espresso.pressBack as espressoPressBack

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleInstrumentedTest : BaseUITest() {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    override fun setUp() {
        super.setUp()
        mockNetworkResponseWithFileContent(
            rawFile = com.cristian.pruebayape.test.R.raw.response,
            responseCode = HttpURLConnection.HTTP_OK,
            socketPolicy = SocketPolicy.CONTINUE_ALWAYS
        )
        activityRule.launchActivity(null)
    }

    @Test
    fun test_recycler_view_is_visible() {
        // when
        onView(withId(R.id.content_recycler))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_complete_flow() {
        // when
        Thread.sleep(4000)
        onView(withId(R.id.content_recycler))
            .check(matches(withEffectiveVisibility(VISIBLE)))
            .perform(swipeUp())

        Thread.sleep(2000)
        onView(withId(R.id.content_recycler))
            .check(matches(withEffectiveVisibility(VISIBLE)))
            .perform(swipeDown())

        onView(withId(R.id.content_recycler))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        // then
        onView(withId(R.id.id_country))
            .check(matches(withText("País de origen: Colombia"))).perform(scrollTo())

        onView(withId(R.id.txt_ingredients))
            .check(matches(withEffectiveVisibility(VISIBLE)))

        onView(withId(R.id.txt_instructions))
            .check(matches(withEffectiveVisibility(VISIBLE)))

        onView(withId(R.id.btn_maps))
            .check(matches(withEffectiveVisibility(VISIBLE)))
            .perform(click())
        Thread.sleep(3000)

    }

    @Test
    fun test_execute_back_navigation() {
        Thread.sleep(4000)
        onView(withId(R.id.content_recycler))
            .check(matches(isDisplayed()))

        onView(withId(R.id.content_recycler))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    click()
                )
            )

        onView(withId(R.id.txt_instructions))
            .check(matches(withEffectiveVisibility(VISIBLE)))

        espressoPressBack()

        onView(withId(R.id.content_recycler))
            .check(matches(isDisplayed()))

    }

    @Test
    fun test_search_recipe() {
        Thread.sleep(4000)
        onView(withId(R.id.content_recycler))
            .check(matches(isDisplayed()))

        onView(withId(R.id.searchView)).perform(
            click()
        )

        onView(isAssignableFrom(EditText::class.java))
            .perform(
                typeText("arr"), pressImeActionButton()
            )

        onView(withId(R.id.content_recycler))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    click()
                )
            )

    }


    override fun tearDown() {
        super.tearDown()
        activityRule.finishActivity()
    }
}
