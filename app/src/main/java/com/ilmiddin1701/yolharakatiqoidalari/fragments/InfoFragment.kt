package com.ilmiddin1701.yolharakatiqoidalari.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ilmiddin1701.yolharakatiqoidalari.R
import com.ilmiddin1701.yolharakatiqoidalari.databinding.FragmentInfoBinding

@Suppress("DEPRECATION")
class InfoFragment : Fragment() {
    private val binding by lazy { FragmentInfoBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            bottomNavigationView.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> findNavController().navigate(R.id.homeFragment)
                    R.id.menu_liked -> findNavController().navigate(R.id.likedFragment)
                }
                true
            }
        }
        return binding.root
    }
}