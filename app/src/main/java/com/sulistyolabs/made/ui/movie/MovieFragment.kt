package com.sulistyolabs.made.ui.movie


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sulistyolabs.made.BuildConfig
import com.sulistyolabs.made.ui.DetailsFilm
import com.sulistyolabs.made.R
import com.sulistyolabs.made.adapter.MovieAdapter
import com.sulistyolabs.made.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.support.v4.startActivity

class MovieFragment : Fragment() {

    private lateinit var adapter: MovieAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(MainViewModel::class.java)

        initData()

        mainViewModel.getMovie().observe(this, Observer { movieItem ->
            if (movieItem != null ) {
                adapter.setData(movieItem)
                showLoading(false)
            }
        })
    }

    private fun initData() {

        val apiKey: String = BuildConfig.API_KEY
        val lang: String = resources.getString(R.string.lang)

        mainViewModel.setMovie(apiKey, lang)
        showLoading(true)

        adapter = MovieAdapter(requireContext()) {
            startActivity<DetailsFilm>("movie" to it)
        }

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_movie?.layoutManager = layoutManager
        rv_movie.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

}
