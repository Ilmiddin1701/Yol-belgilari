package com.ilmiddin1701.yolharakatiqoidalari.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ilmiddin1701.yolharakatiqoidalari.R

class SpinnerAdapter(private var list: ArrayList<String>) : BaseAdapter() {
    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView: View =
            convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.item_spinner, parent, false)
        itemView.findViewById<TextView>(R.id.tvSpinner).text = list[position]
        return itemView
    }
}