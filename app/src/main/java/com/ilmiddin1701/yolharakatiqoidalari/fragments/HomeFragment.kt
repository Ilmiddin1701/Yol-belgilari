package com.ilmiddin1701.yolharakatiqoidalari.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import com.google.android.material.tabs.TabLayout
import com.ilmiddin1701.yolharakatiqoidalari.R
import com.ilmiddin1701.yolharakatiqoidalari.adapters.VpAdapter
import com.ilmiddin1701.yolharakatiqoidalari.databinding.FragmentHomeBinding

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    lateinit var vpAdapter: VpAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vpAdapter = VpAdapter(childFragmentManager)
        binding.apply {
            btnAdd.setOnClickListener {
                findNavController().navigate(R.id.addSymbolFragment, bundleOf("keyPosition" to 0))
            }
            vp.adapter = vpAdapter
            tabLayout.setupWithViewPager(vp)
            setTabLayout()
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(p0: TabLayout.Tab?) {
                    val customView = p0?.customView
                    customView?.findViewById<MaterialCardView>(R.id.card)?.strokeWidth = 0
                    customView?.findViewById<LinearLayout>(R.id.tabLinear)?.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    customView?.findViewById<TextView>(R.id.tabText)?.setTextColor(Color.parseColor("#005CA1"))
                }
                override fun onTabUnselected(p0: TabLayout.Tab?) {
                    val customView = p0?.customView
                    customView?.findViewById<MaterialCardView>(R.id.card)?.strokeWidth = 2
                    customView?.findViewById<LinearLayout>(R.id.tabLinear)?.setBackgroundColor(Color.parseColor("#005CA1"))
                    customView?.findViewById<TextView>(R.id.tabText)?.setTextColor(Color.parseColor("#FFFFFF"))
                }
                override fun onTabReselected(p0: TabLayout.Tab?) {}
            })
            bottomNavigationView.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_liked -> {
                        findNavController().navigate(R.id.likedFragment)
                    }
                    R.id.menu_info -> {
                        findNavController().navigate(R.id.infoFragment)
                    }
                }
                true
            }
        }
        return binding.root
    }

    @SuppressLint("InflateParams")
    private fun setTabLayout() {
        binding.apply {
            val tabCount = tabLayout.tabCount
            val list = arrayOf("Ogohlantiruvchi", "Imtiyozli", "Ta'qiqlovchi", "Buyuruvchi")
            for (i in 0 until tabCount) {
                val tabView = layoutInflater.inflate(R.layout.item_tab_layout, null, false)
                val tab = tabLayout.getTabAt(i)
                tab?.customView = tabView

                val card = tabView.findViewById<MaterialCardView>(R.id.card)
                val linear = tabView.findViewById<LinearLayout>(R.id.tabLinear)
                val tv = tabView.findViewById<TextView>(R.id.tabText)

                if (i == 0) {
                    card.strokeWidth = 0
                    linear.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    tv.setTextColor(Color.parseColor("#005CA1"))
                } else {
                    card.strokeWidth = 2
                    linear.setBackgroundColor(Color.parseColor("#005CA1"))
                    tv.setTextColor(Color.parseColor("#FFFFFF"))
                }
                tv.text = list[i]
            }
        }
    }
}