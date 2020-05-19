package com.kartdroid.layoutdemo.home.adapter

interface AdapterViewItemClickListener<T> {
    fun onItemClicked(position: Int, item: T)
}