package com.ilmiddin1701.yolharakatiqoidalari.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.ilmiddin1701.yolharakatiqoidalari.R
import com.ilmiddin1701.yolharakatiqoidalari.adapters.RvAdapter1
import com.ilmiddin1701.yolharakatiqoidalari.databinding.FragmentLikedBinding
import com.ilmiddin1701.yolharakatiqoidalari.db.MyDbHelper
import com.ilmiddin1701.yolharakatiqoidalari.models.SymbolData
import com.ilmiddin1701.yolharakatiqoidalari.utils.MyData

@Suppress("DEPRECATION")
class LikedFragment : Fragment(), RvAdapter1.RvAction1 {
    private val binding by lazy { FragmentLikedBinding.inflate(layoutInflater) }
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var rvAdapter1: RvAdapter1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            bottomNavigationView.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> findNavController().navigate(R.id.homeFragment)
                    R.id.menu_info -> findNavController().navigate(R.id.infoFragment)
                }
                true
            }
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        return binding.root
    }

    override fun itemClick(symbolData: SymbolData) {
        MyData.data = symbolData
        findNavController().navigate(R.id.symbolViewFragment)
    }
    override fun likeClick(symbolData: SymbolData) {
        if (symbolData.isLiked == 0){
            symbolData.isLiked = 1
        } else if (symbolData.isLiked == 1) {
            symbolData.isLiked = 0
        }
        myDbHelper = MyDbHelper(requireContext())
        myDbHelper.editSymbol(symbolData)
        onResume()
    }
    override fun editClick(symbolData: SymbolData) {
        MyData.data = symbolData
        findNavController().navigate(R.id.addSymbolFragment, bundleOf("keyPosition" to 1))
    }
    override fun deleteClick(symbolData: SymbolData) {
        myDbHelper = MyDbHelper(requireContext())
        myDbHelper.deleteSymbol(symbolData)
        onResume()
    }

    override fun onResume() {
        super.onResume()
        myDbHelper = MyDbHelper(requireContext())
        val list = ArrayList<SymbolData>()
        myDbHelper.showSymbols().forEach {
            if (it.isLiked == 1){
                list.add(it)
            }
        }
        rvAdapter1 = RvAdapter1(this@LikedFragment, list)
        binding.rv.adapter = rvAdapter1
    }
}