package com.sulistyolabs.made.ui.favorite.favtv


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sulistyolabs.made.R
import com.sulistyolabs.made.adapter.TvShowAdapter
import com.sulistyolabs.made.data.model.TvItem
import com.sulistyolabs.made.ui.DetailsFilm
import kotlinx.android.synthetic.main.fragment_fav_tv.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class FavTv : Fragment(), FavTvInterface.View {

    private lateinit var adapter: TvShowAdapter
    private var listTv: MutableList<TvItem> = mutableListOf()

    private lateinit var mPresenter: FavTvPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mPresenter = FavTvPresenter(this)
        mPresenter.getTvData(requireContext())

        adapter = TvShowAdapter(requireContext()) {
            startActivity<DetailsFilm>("tvshow" to it)
        }

        adapter.setData(listTv)
        rv_fav_tv.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_tv, container, false)
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showLoading() {
        progressBar.visibility = View.INVISIBLE

    }

    override fun showDavoriteTv(movieList: List<TvItem>) {

        listTv.clear()
        listTv.addAll(movieList)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_fav_tv.layoutManager = layoutManager
    }


}
