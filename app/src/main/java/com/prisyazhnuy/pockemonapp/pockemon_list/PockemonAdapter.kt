package com.prisyazhnuy.pockemonapp.pockemon_list

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.prisyazhnuy.pockemonapp.model.Pockemon
import org.jetbrains.anko.*


class PockemonAdapter(private val resultList: MutableList<Pockemon>?) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface Callback {
        fun onPockemonClick(pockemon: Pockemon?)
    }

    private var callback: Callback? = null

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    fun addItems(resultList: List<Pockemon>) {

        val newResultList = ArrayList<Pockemon>()
        this.resultList?.let { newResultList.addAll(it) }
        newResultList.addAll(resultList)

        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(p0: Int, p1: Int) = this@PockemonAdapter.resultList?.get(p0) == resultList[p1]

            override fun getOldListSize() = this@PockemonAdapter.resultList?.size ?: 0

            override fun getNewListSize() = resultList.size

            override fun areContentsTheSame(p0: Int, p1: Int) = this@PockemonAdapter.resultList?.get(p0) == resultList[p1]

        }
        )
        this.resultList?.addAll(resultList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun clearItems() {
        resultList?.clear()
        notifyDataSetChanged()
    }

    fun addItem(pockemon: Pockemon) {
        resultList?.run {
            add(pockemon)
            notifyItemInserted(size - 1)
        }
    }

    fun removeItem() {
        resultList?.run {
            removeAt(size - 1)
            notifyItemRemoved(size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PockemonViewHolder {
        return PockemonViewHolder(PockemonUI().createView(AnkoContext.create(parent.context, parent)))
    }

    fun getItem(position: Int): Pockemon? {
        return if (position != RecyclerView.NO_POSITION)
            resultList?.get(position)
        else
            null
    }

    override fun getItemCount(): Int {
        return resultList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? PockemonViewHolder)?.run {
            tvTitle.text = resultList?.get(position)?.name
            llContainer.setOnClickListener {
                callback?.onPockemonClick(resultList?.get(position))
            }
        }
    }

    inner class PockemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle: TextView
        var ivIcon: ImageView
        var llContainer: LinearLayout

        init {
            tvTitle = itemView.findViewById(PockemonUI.tvTitleId)
            ivIcon = itemView.findViewById(PockemonUI.ivIconId)
            llContainer = itemView.findViewById(PockemonUI.llContainer)
        }

    }
}