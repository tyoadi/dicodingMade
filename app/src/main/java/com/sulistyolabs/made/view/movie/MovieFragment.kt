package com.sulistyolabs.made.view.movie


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sulistyolabs.made.R
import com.sulistyolabs.made.adapter.MovieAdapter
import com.sulistyolabs.made.model.Movie
import com.sulistyolabs.made.model.MovieItem
import com.sulistyolabs.made.utils.invisible
import com.sulistyolabs.made.utils.visible
import com.sulistyolabs.made.view.DetailsFilm
import kotlinx.android.synthetic.main.error_loading.*
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.toast

class MovieFragment : Fragment(), MovieContract.View {

    private lateinit var adapter: MovieAdapter
    private var items: MutableList<MovieItem> = mutableListOf()
    private lateinit var mPresenter: MoviePresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mPresenter = MoviePresenter(this)

        initData()
    }

    private fun initData() {

        val apiKey: String = resources.getString(R.string.api_key)
        val lang: String = resources.getString(R.string.languange)
        mPresenter.getDataMovie(apiKey, lang)
        adapter = MovieAdapter(requireContext(), items) {
            startActivity<DetailsFilm>("movie" to it)
        }
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_movie?.layoutManager = layoutManager
        rv_movie.adapter = adapter

    }


    override fun showListMovie(data: List<MovieItem>) {
        items.clear()
        data?.let { items.addAll(it) }
        adapter.notifyDataSetChanged()
    }



    override fun onEmpty() {
        rv_movie.invisible()
        container_error.visible()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun onFailed(message: String) {
        requireContext().toast(message.toString())
        Log.e("error loading", message)
        rv_movie.invisible()
        progressBar.invisible()
        container_error.visible()

    }

    override fun onError(message: String) {
        requireContext().toast(message.toString())
        Log.e("error loading", message)
        rv_movie.invisible()
        progressBar.invisible()
        container_error.visible()

    }

}
