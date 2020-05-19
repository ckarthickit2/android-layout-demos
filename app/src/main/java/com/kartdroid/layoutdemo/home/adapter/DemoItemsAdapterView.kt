package com.kartdroid.layoutdemo.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kartdroid.layoutdemo.home.model.DemoItem

interface PositionClickListener {
    fun onPositionClick(position: Int)
}

class DemoItemsAdapterView(private val demoItems: List<DemoItem>) :
    RecyclerView.Adapter<DemoItemViewHolder>(), PositionClickListener {

    private var adapterViewItemClickListener: AdapterViewItemClickListener<DemoItem>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoItemViewHolder {
        return DemoItemViewHolder(LayoutInflater.from(parent.context), this)
    }

    override fun getItemCount(): Int {
        return demoItems.count()
    }

    override fun onBindViewHolder(holder: DemoItemViewHolder, position: Int) {
        val demoItem = demoItems[position]
        holder.titleText.text = demoItem.title
        holder.currentBoundPosition = position
    }

    fun itemAt(position: Int): DemoItem {
        return demoItems[position]
    }

    fun setItemClickListener(listener: AdapterViewItemClickListener<DemoItem>) {
        this.adapterViewItemClickListener = listener
    }

    override fun onPositionClick(position: Int) {
        adapterViewItemClickListener?.onItemClicked(position, demoItems[position])
    }
}