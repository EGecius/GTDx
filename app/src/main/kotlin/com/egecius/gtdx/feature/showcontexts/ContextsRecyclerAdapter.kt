package com.egecius.gtdx.feature.showcontexts

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.egecius.gtdx.R
import com.egecius.gtdx.datatypes.ContextItem
import java.util.*

internal class ContextsRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface Callback {
        fun onContextClicked(item: ContextItem)
    }

    private val list = ArrayList<ContextItem>()
    var callback : Callback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.context_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MyViewHolder) {

            holder.taskRoot.setOnClickListener { callback?.onContextClicked(list[position]) }

            val titleText = list[position].title
            holder.title.text = titleText
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: List<ContextItem>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    private inner class MyViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskRoot: View = itemView.findViewById(R.id.taskRoot) as ViewGroup
        val title: TextView = itemView.findViewById(R.id.title) as TextView
    }
}

