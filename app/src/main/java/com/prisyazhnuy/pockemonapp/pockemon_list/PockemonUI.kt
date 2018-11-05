package com.prisyazhnuy.pockemonapp.pockemon_list

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*

class PockemonUI : AnkoComponent<ViewGroup>{

    companion object {
        val tvTitleId = 1
        val ivIconId = 2
        val llContainer = 3
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){
        linearLayout {
            id = llContainer
            lparams(matchParent, wrapContent)
            padding = dip(16)

            imageView {
                id = ivIconId
                layoutParams = LinearLayout.LayoutParams(50, 50)
            }

            textView {
                id = tvTitleId
                layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent)
                textSize = 16f // <- it is sp
                textColor = Color.BLACK
            }
        }
    }
}
