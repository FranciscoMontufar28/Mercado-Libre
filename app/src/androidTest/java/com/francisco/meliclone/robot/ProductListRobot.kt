package com.francisco.meliclone.robot

import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.francisco.meliclone.R
import com.francisco.meliclone.util.CurrentActivity.Companion.getActivityInstance
import com.francisco.meliclone.util.click
import com.francisco.meliclone.util.clickOnRecyclerAtPosition
import com.francisco.meliclone.util.verifyIsDisplayed
import com.francisco.meliclone.util.writeInSearchView

fun productList(func: ProductListRobot.() -> Unit) {
    ProductListRobot().apply(func)
}

class ProductListRobot {

    fun clickOnProductAtPosition(position: Int) {
        R.id.product_list_recycler_view.clickOnRecyclerAtPosition(position)
    }

    fun typeInSearch(text: String) {
        R.id.app_bar_search.click()
        R.id.app_bar_search.writeInSearchView(text)
    }

    fun verifyProductFragmentIsDisplayed() {
        R.id.product_list_recycler_view.verifyIsDisplayed()
    }

    fun getTitleInRecycler(position: Int): String {
        Thread.sleep(1000)
        val recyclerView = getActivityInstance()?.findViewById<RecyclerView>(R.id.product_list_recycler_view)
        val cardView = recyclerView?.getChildAt(position) as CardView
        return cardView.findViewById<TextView>(R.id.tv_product_title).text.toString()
    }
}