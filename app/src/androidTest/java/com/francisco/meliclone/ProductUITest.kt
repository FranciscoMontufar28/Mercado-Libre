package com.francisco.meliclone

import androidx.test.rule.ActivityTestRule
import com.francisco.meliclone.robot.details
import com.francisco.meliclone.robot.productList
import org.junit.Rule
import org.junit.Test

class ProductUITest {

    @get:Rule
    val activityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java)

    @Test
    fun test_navigation_and_search(){
        lateinit var title : String
        Thread.sleep(2000)
        productList {
            typeInSearch("xbox")
            title = getTitleInRecycler(1)
            clickOnProductAtPosition(1)
        }
        details {
            verifyDetailFragmentIsDisplayed()
            verifyTitleDetails(title)
            pressBack()
        }
        productList {
            verifyProductFragmentIsDisplayed()
        }
    }

    @Test
    fun test_navigation_and_no_search(){
        lateinit var title : String
        Thread.sleep(2000)
        productList {
            title = getTitleInRecycler(1)
            clickOnProductAtPosition(1)
        }
        details {
            verifyDetailFragmentIsDisplayed()

            verifyTitleDetails(title)
            pressBack()
        }
        productList {
            verifyProductFragmentIsDisplayed()
        }
    }
}