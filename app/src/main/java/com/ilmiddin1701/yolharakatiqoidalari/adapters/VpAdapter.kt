package com.ilmiddin1701.yolharakatiqoidalari.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ilmiddin1701.yolharakatiqoidalari.fragments.ItemVpFragments.ItemViewPager1Fragment
import com.ilmiddin1701.yolharakatiqoidalari.fragments.ItemVpFragments.ItemViewPager2Fragment
import com.ilmiddin1701.yolharakatiqoidalari.fragments.ItemVpFragments.ItemViewPager3Fragment
import com.ilmiddin1701.yolharakatiqoidalari.fragments.ItemVpFragments.ItemViewPager4Fragment

@Suppress("DEPRECATION")
class VpAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int = 4

    override fun getItem(position: Int): Fragment {
        return when(position){
            0->{ ItemViewPager1Fragment() }
            1->{ ItemViewPager2Fragment() }
            2->{ ItemViewPager3Fragment() }
            else -> { ItemViewPager4Fragment() }
        }
    }
}