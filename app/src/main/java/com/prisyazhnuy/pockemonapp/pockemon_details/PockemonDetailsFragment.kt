package com.prisyazhnuy.pockemonapp.pockemon_details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.prisyazhnuy.pockemonapp.model.PockemonItem
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find

class PockemonDetailsFragment : Fragment() {

    companion object {
        private const val EXTRA_NAME = "name"
        fun newInstance(name: String) = PockemonDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(EXTRA_NAME, name)
            }
        }
    }

    private lateinit var viewModel: PockemonDetailsVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return PockemonDetailsUI().createView(AnkoContext.create(ctx, this))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PockemonDetailsVM::class.java)
        viewModel.apply {
            loadPockemon(arguments?.getString(EXTRA_NAME))
            pockemonLD.observe(this@PockemonDetailsFragment, Observer { showPockemon(it) })
            pockemonIcon.observe(this@PockemonDetailsFragment, Observer {
                find<ImageView>(PockemonDetailsUI.ivIcon).setImageBitmap(it)
            })
        }
    }

    private fun showPockemon(item: PockemonItem?) {
        item?.let {
            find<TextView>(PockemonDetailsUI.tvName).text = it.name
            viewModel.loadImage(it.sprites.front_default)
        }
    }

}