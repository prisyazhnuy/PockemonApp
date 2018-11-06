package com.prisyazhnuy.pockemonapp.pockemon_list

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.prisyazhnuy.pockemonapp.model.PockemonModel
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast

class PockemonListFragment : Fragment(),
        PockemonAdapter.Callback {

    companion object {
        fun newInstance() = PockemonListFragment()
    }

    private lateinit var viewModel: PockemonListVM

    private var viewAdapter = PockemonAdapter(mutableListOf()).apply {
        setCallback(this@PockemonListFragment)
        addItem(PockemonModel(name = "Pikachu"))
        addItem(PockemonModel(name = "Pikachu"))
        addItem(PockemonModel(name = "Pikachu"))
        addItem(PockemonModel(name = "Pikachu"))
        addItem(PockemonModel(name = "Pikachu"))
        addItem(PockemonModel(name = "Pikachu"))
        addItem(PockemonModel(name = "Pikachu"))
        addItem(PockemonModel(name = "Pikachu"))
        addItem(PockemonModel(name = "Pikachu"))
        addItem(PockemonModel(name = "Pikachu"))
        addItem(PockemonModel(name = "Pikachu"))
        addItem(PockemonModel(name = "Pikachu"))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return PockemonListUI(viewAdapter).createView(AnkoContext.create(ctx, this))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PockemonListVM::class.java)
    }

    override fun onPockemonClick(id: Long) {
        toast("Pockemon clicked")
    }

}
