package com.francisco.meliclone.robot

import com.francisco.meliclone.R
import com.francisco.meliclone.util.pressBackButton
import com.francisco.meliclone.util.verifyIsDisplayed
import com.francisco.meliclone.util.verifyText

fun details(func: DetailsRobot.() -> Unit){
    DetailsRobot().apply(func)
}

class DetailsRobot {
    fun verifyTitleDetails(title: String){
        R.id.product_detail_title.verifyText(title)
    }

    fun pressBack(){
        pressBackButton()
    }

    fun verifyDetailFragmentIsDisplayed(){
        R.id.btn_buy.verifyIsDisplayed()
    }
}