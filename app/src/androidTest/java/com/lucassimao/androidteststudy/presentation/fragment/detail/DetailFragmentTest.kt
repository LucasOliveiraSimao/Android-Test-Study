package com.lucassimao.androidteststudy.presentation.fragment.detail

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.lucassimao.androidteststudy.R
import com.lucassimao.androidteststudy.baseUITest.BaseUITest
import org.hamcrest.CoreMatchers
import org.junit.Test

class DetailFragmentTest : BaseUITest() {

    @Test
    fun shouldDisplayName() {
        navigateToPlaylistDetail()

        assertDisplayed("Hard Rock Cafe")
    }

    private fun navigateToPlaylistDetail() {
        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withId(R.id.playlist_name),
                ViewMatchers.isDescendantOfA(
                    nthChildOf(
                        ViewMatchers.withId(R.id.rv_playlist_list),
                        0
                    )
                )
            )
        )
            .perform(ViewActions.click())
    }
}