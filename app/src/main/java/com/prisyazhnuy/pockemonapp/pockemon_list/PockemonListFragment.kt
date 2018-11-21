package com.prisyazhnuy.pockemonapp.pockemon_list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prisyazhnuy.pockemonapp.model.Pockemon
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
    }

    private var callback: PockemonCallback? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callback = context as? PockemonCallback
    }

    override fun onDetach() {
        callback = null
        super.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return PockemonListUI(viewAdapter).createView(AnkoContext.create(ctx, this))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PockemonListVM::class.java)
        viewModel.apply {
            if (viewAdapter.itemCount == 0) loadInit()
            pockemonsLD.observe(this@PockemonListFragment, Observer { it?.let { viewAdapter.addItems(it) } })
            errorLD.observe(this@PockemonListFragment, Observer { toast(it?.message.toString()) })
        }
    }

    override fun onPockemonClick(pockemon: Pockemon?) {
        Log.d("tag", "Pockemon clicked id = ${pockemon?.url}")
        pockemon?.name?.let { callback?.showPockemonDetails(it) }
    }

}
