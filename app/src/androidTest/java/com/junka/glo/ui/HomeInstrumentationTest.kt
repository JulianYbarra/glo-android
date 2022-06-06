package com.junka.glo.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import com.junka.glo.R
import com.junka.glo.data.server.MockWebServerRule
import com.junka.glo.data.server.fromJson
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
class HomeInstrumentationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @get:Rule(order = 2)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        mockWebServerRule.server.enqueue(
            MockResponse().fromJson("products.json")
        )

        hiltRule.inject()

        val resource = OkHttp3IdlingResource.create("OkHttp", okHttpClient)
        IdlingRegistry.getInstance().register(resource)
    }

    @Test
    fun click_a_product_navigates_to_detail() {
        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    5, ViewActions.click()
                )
            )

        onView(withId(R.id.toolbar))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("Laptop 6"))))

    }
}