package com.prisyazhnuy.pockemonapp.pockemon_list

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class PockemonListUI(private val pockemonAdapter: PockemonAdapter) : AnkoComponent<PockemonListFragment> {
    companion object {
        val recyclerViewId = 1
    }

    override fun createView(ui: AnkoContext<PockemonListFragment>): View = with(ui) {
        return@with relativeLayout {
            recyclerView {
                id = recyclerViewId
                val orientation = LinearLayoutManager.VERTICAL
                layoutManager = LinearLayoutManager(context, orientation, true)
                overScrollMode = View.OVER_SCROLL_NEVER
                adapter = pockemonAdapter
            }.lparams(width = matchParent, height = wrapContent)
        }
    }

}