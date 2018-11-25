package com.prisyazhnuy.pockemonapp.pockemon_details

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.prisyazhnuy.pockemonapp.R
import org.jetbrains.anko.*

class PockemonDetailsUI : AnkoComponent<PockemonDetailsFragment> {
    companion object {
        const val tvName = 1
        const val ivIcon = 2
        const val progressBarId = 3
        const val tvWeight = 4
        const val tvOrder = 5
        const val tvHeight = 6
    }

    override fun createView(ui: AnkoContext<PockemonDetailsFragment>): View = with(ui) {
        return@with relativeLayout {
            imageView {
                id = ivIcon
                scaleType = ImageView.ScaleType.FIT_XY
            }.lparams(300, 300)

            linearLayout {
                orientation = LinearLayout.VERTICAL

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL

                    textView {
                        layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                        textSize = 16f // <- it is sp
                        textColor = Color.BLACK
                        textResource = R.string.name
                    }

                    textView {
                        id = tvName
                        layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent).apply {
                            setPadding(16, 0, 16, 0)
                        }
                        textSize = 16f // <- it is sp
                        textColor = Color.BLACK
                    }

                }.lparams(matchParent, wrapContent)

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL

                    textView {
                        layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                        textSize = 16f // <- it is sp
                        textColor = Color.BLACK
                        textResource = R.string.weight
                    }

                    textView {
                        id = tvWeight
                        layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent).apply {
                            setPadding(16, 0, 16, 0)
                        }
                        textSize = 16f // <- it is sp
                        textColor = Color.BLACK
                    }

                }.lparams(matchParent, wrapContent)

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL

                    textView {
                        layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                        textSize = 16f // <- it is sp
                        textColor = Color.BLACK
                        textResource = R.string.order
                    }

                    textView {
                        id = tvOrder
                        layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent).apply {
                            setPadding(16, 0, 16, 0)
                        }
                        textSize = 16f // <- it is sp
                        textColor = Color.BLACK
                    }

                }.lparams(matchParent, wrapContent)

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL

                    textView {
                        layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                        textSize = 16f // <- it is sp
                        textColor = Color.BLACK
                        textResource = R.string.height
                    }

                    textView {
                        id = tvHeight
                        layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent).apply {
                            setPadding(16, 0, 16, 0)
                        }
                        textSize = 16f // <- it is sp
                        textColor = Color.BLACK
                    }

                }.lparams(matchParent, wrapContent)

            }.lparams(matchParent, wrapContent) {
                endOf(ivIcon)
            }

            relativeLayout {
                id = progressBarId
                visibility = View.GONE

                progressBar {}.lparams(100, 100) {
                    centerInParent()
                }
            }.lparams(matchParent, matchParent)
        }
    }

}