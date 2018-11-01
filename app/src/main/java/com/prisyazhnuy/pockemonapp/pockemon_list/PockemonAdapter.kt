package com.prisyazhnuy.pockemonapp.pockemon_list

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prisyazhnuy.pockemonapp.model.Pockemon

interface AdapterCallback {
    fun onClick(position: Int)
}

class PockemonAdapter(context: Context, data: List<Pockemon> = listOf()) : RecyclerView.Adapter<PockemonAdapter.ViewHolder>() {

    protected val context: Context = context.applicationContext
    protected val inflater: LayoutInflater = LayoutInflater.from(context)
    protected val data: MutableList<Pockemon> = data.toMutableList()

    override fun getItemCount(): Int = this.data.size

    fun isEmpty() = this.data.isEmpty()

    @Throws(ArrayIndexOutOfBoundsException::class)
    fun getItem(position: Int): Pockemon = this.data[position]

    fun add(`object`: Pockemon): Boolean = this.data.add(`object`)

    fun add(oldPosition: Int, newPosition: Int) = this.data.add(newPosition, remove(oldPosition))

    operator fun set(position: Int, `object`: Pockemon): Pockemon = this.data.set(position, `object`)

    fun remove(`object`: Pockemon): Boolean = this.data.remove(`object`)

    fun remove(position: Int): Pockemon = this.data.removeAt(position)

    fun updateListItems(newObjects: List<Pockemon>, callback: DiffUtil.Callback) {
        DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
        data.clear()
        data.addAll(newObjects)
    }

    val all: List<Pockemon> = this.data

    open fun clear() {
        this.data.clear()
    }

    fun addAll(collection: Collection<Pockemon>): Boolean = this.data.addAll(collection)

    val snapshot: List<Pockemon> = data.toMutableList()

    fun getItemPosition(`object`: Pockemon) = this.data.indexOf(`object`)

    fun insert(`object`: Pockemon, position: Int) {
        this.data.add(position, `object`)
    }

    fun insertAll(`object`: Collection<Pockemon>, position: Int) {
        this.data.addAll(position, `object`)
    }

    class ViewHolder(itemView: View, private val callback: AdapterCallback?) :
            RecyclerView.ViewHolder(itemView),
            View.OnClickListener {

        companion object {
            internal fun newInstance(inflater: LayoutInflater, parent: ViewGroup?, callback: AdapterCallback?) =
                    ViewHolder(inflater.inflate(R.layout.item_event, parent, false), callback)
        }

        private val rlEventContainer = itemView.find<RelativeLayout>(R.id.rlEventContainer)

        override fun bind(item: Event) {
            with(item) {

            setClickListeners(rlEventContainer)
        }

        override fun onClick(view: View?) {
            when (view?.id) {
                R.id.rlEventContainer -> callback?.onClick(adapterPosition)
            }
        }

    }
}