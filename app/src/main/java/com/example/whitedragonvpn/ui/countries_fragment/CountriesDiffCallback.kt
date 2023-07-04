package com.example.whitedragonvpn.ui.countries_fragment

import androidx.recyclerview.widget.DiffUtil

class CountriesDiffCallback<T>(
    private val oldList: List<T>,
    private val newList: List<T>,
    private val areItemsTheSameImpl: (oldItem: T, newItem: T) -> Boolean =
        { oldItem, newItem -> oldItem == newItem },
    private val areContentsTheSameImpl: (oldItem: T, newItem: T) -> Boolean =
        { oldItem, newItem -> oldItem == newItem }
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return areItemsTheSameImpl(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return areContentsTheSameImpl(oldItem, newItem)
    }

}