package com.prisyazhnuy.pockemonapp.pockemon_list

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class PockemonListUI(private val pockemonAdapter: PockemonAdapter) : AnkoComponent<PockemonListFragment> {
    companion object {
        val recyclerViewId = 1
        val progressBarId = 2
    }

    override fun createView(ui: AnkoContext<PockemonListFragment>): View = with(ui) {
        return@with relativeLayout {
            recyclerView {
                id = recyclerViewId
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                overScrollMode = View.OVER_SCROLL_NEVER
                clipToPadding = false
                adapter = pockemonAdapter
            }.lparams(width = matchParent, height = wrapContent)

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