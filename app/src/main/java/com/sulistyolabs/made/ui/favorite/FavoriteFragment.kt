package com.sulistyolabs.made.ui.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sulistyolabs.made.R
import com.sulistyolabs.made.adapter.FragmentAdapter
import com.sulistyolabs.made.ui.favorite.favmovie.FavMovie
import com.sulistyolabs.made.ui.favorite.favtv.FavTv
import kotlinx.android.synthetic.main.fragment_favorite.*


class FavoriteFragment : Fragment() {

    lateinit var pagesAdapter: FragmentAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pagesAdapter = FragmentAdapter(childFragmentManager)
        pagesAdapter.addFragment(FavMovie(),"Match")
        pagesAdapter.addFragment(FavTv(),"Movie")
        pagerTab.adapter = pagesAdapter
        tab.setupWithViewPager(pagerTab)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }


}
