package com.example.lotteryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class lottolyListAdapter(val dataList: List<String>)
    :RecyclerView.Adapter<lottolyListAdapter.ItemViewHolder>()
{
    class ItemViewHolder(val view: View)
        :RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.lotto_item
    }
}