package com.prisyazhnuy.pockemonapp.pockemon_list

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.prisyazhnuy.pockemonapp.R
import kotlinx.android.synthetic.main.fragment_pockemon_list.*

class PockemonListFragment : Fragment(),
        PockemonAdapter.Callback {

    companion object {
        fun newInstance() = PockemonListFragment()
    }

    private lateinit var viewModel: PockemonListVM

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pockemon_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PockemonListVM::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        viewManager = LinearLayoutManager(requireContext())
        viewAdapter = PockemonAdapter(mutableListOf()).apply { setCallback(this@PockemonListFragment) }

        rvPockemons.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }

    override fun onPockemonClick(id: Long) {

    }

}
