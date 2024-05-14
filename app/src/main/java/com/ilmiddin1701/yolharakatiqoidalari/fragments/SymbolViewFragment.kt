package com.ilmiddin1701.yolharakatiqoidalari.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ilmiddin1701.yolharakatiqoidalari.databinding.FragmentSymbolViewBinding
import com.ilmiddin1701.yolharakatiqoidalari.utils.MyData

class SymbolViewFragment : Fragment() {
    private val binding by lazy { FragmentSymbolViewBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            imgSymbol.setImageURI(Uri.parse(MyData.data?.image))
            tvTitle.text = MyData.data?.name
            tvName.text = MyData.data?.name
            tvAbout.text = MyData.data?.about
        }
        return binding.root
    }
}