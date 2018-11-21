package com.prisyazhnuy.pockemonapp.pockemon_details

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import org.jetbrains.anko.*

class PockemonDetailsUI : AnkoComponent<PockemonDetailsFragment> {
    companion object {
        val tvName = 1
        val ivIcon = 2
    }

    override fun createView(ui: AnkoContext<PockemonDetailsFragment>): View = with(ui) {
        return@with relativeLayout {
            imageView {
                id = ivIcon
                scaleType = ImageView.ScaleType.FIT_XY
            }.lparams(300,300)

            textView {
                id = tvName
                layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent)
                textSize = 16f // <- it is sp
                textColor = Color.BLACK
            }.lparams {
                endOf(ivIcon)
            }


        }
    }

}