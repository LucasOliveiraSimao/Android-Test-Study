package com.lucassimao.androidteststudy.presentation.fragment.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import com.lucassimao.androidteststudy.R
import com.lucassimao.androidteststudy.baseUITest.BaseUITest
import com.lucassimao.androidteststudy.presentation.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test

class HomeFragmentTest : BaseUITest() {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun shouldDisplayScreenTitle() {
        assertDisplayed("Playlist")
    }

    @Test
    fun shouldDisplayListOfPlaylist() {
        Thread.sleep(6000)

        onView(
            allOf(
                withId(R.id.playlist_name),
                isDescendantOfA(nthChildOf(withId(R.id.rv_playlist_list), 0))
            )
        )
            .check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.playlist_category),
                isDescendantOfA(nthChildOf(withId(R.id.rv_playlist_list), 0))
            )
        )
            .check(matches(withText("rock")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.playlist_image),
                isDescendantOfA(nthChildOf(withId(R.id.rv_playlist_list), 0))
            )
        )
            .check(matches(withDrawable(R.mipmap.rock)))
            .check(matches(isDisplayed()))

    }

    @Test
    fun shouldDisplayLoader_whileFetchingPlaylist() {
        assertDisplayed(R.id.pb_loader)
    }

    @Test
    fun hiderLoader() {
        Thread.sleep(4000)
        assertNotDisplayed(R.id.pb_loader)
    }

    @Test
    fun navigateToDetailScreen() {
        Thread.sleep(6000)

        onView(
            allOf(
                withId(R.id.playlist_name),
                isDescendantOfA(nthChildOf(withId(R.id.rv_playlist_list), 0))
            )
        )
            .perform(click())

        assertDisplayed(R.id.detail_root)

    }
}