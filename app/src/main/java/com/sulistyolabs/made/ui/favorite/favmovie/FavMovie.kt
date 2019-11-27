package com.sulistyolabs.made.ui.favorite.favmovie


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sulistyolabs.made.R
import com.sulistyolabs.made.adapter.MovieAdapter
import com.sulistyolabs.made.data.model.MovieItem
import com.sulistyolabs.made.ui.DetailsFilm
import kotlinx.android.synthetic.main.fragment_fav_movie.*
import org.jetbrains.anko.support.v4.startActivity

class FavMovie : Fragment(), FavMovieInterface.View {

    private lateinit var adapter: MovieAdapter
    private var listMovie: MutableList<MovieItem> = mutableListOf()
    private lateinit var mPresenter: FavMoviePresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter = FavMoviePresenter(this)

        mPresenter.getMovieData(requireContext())
        adapter = MovieAdapter(requireContext()) {
            startActivity<DetailsFilm>("movie" to it)
        }
        adapter.setData(listMovie)

        rv_fav_movie.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_movie, container, false)
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE

    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE

    }

    override fun showDavoriteMovie(movieList: List<MovieItem>) {

        listMovie.clear()
        listMovie.addAll(movieList)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_fav_movie.layoutManager =layoutManager

    }
}
