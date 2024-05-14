package com.ilmiddin1701.yolharakatiqoidalari.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ilmiddin1701.yolharakatiqoidalari.R
import com.ilmiddin1701.yolharakatiqoidalari.databinding.ItemVpRvBinding
import com.ilmiddin1701.yolharakatiqoidalari.models.SymbolData
import java.util.ArrayList

class RvAdapter1(var rvAction1: RvAction1, var list: ArrayList<SymbolData>): Adapter<RvAdapter1.Vh>() {

    inner class Vh(var itemVpRvBinding: ItemVpRvBinding): ViewHolder(itemVpRvBinding.root){
        fun onBind(symbolData: SymbolData){
            itemVpRvBinding.tvName.text = symbolData.name
            itemVpRvBinding.imgSymbol.setImageURI(Uri.parse(symbolData.image))
            itemVpRvBinding.root.setOnClickListener {
                rvAction1.itemClick(symbolData)
            }
            itemVpRvBinding.btnLike.setOnClickListener {
                rvAction1.likeClick(symbolData)
            }
            if (symbolData.isLiked == 0){
                itemVpRvBinding.btnLike.setImageResource(R.drawable.ic_heart_2)
            } else if (symbolData.isLiked == 1) {
                itemVpRvBinding.btnLike.setImageResource(R.drawable.ic_heart_3)
            }
            itemVpRvBinding.btnEdit.setOnClickListener {
                rvAction1.editClick(symbolData)
            }
            itemVpRvBinding.btnDelete.setOnClickListener {
                rvAction1.deleteClick(symbolData)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemVpRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    interface RvAction1{
        fun itemClick(symbolData: SymbolData)
        fun likeClick(symbolData: SymbolData)
        fun editClick(symbolData: SymbolData)
        fun deleteClick(symbolData: SymbolData)
    }
}