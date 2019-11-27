package com.sulistyolabs.made.ui.tvshow


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sulistyolabs.made.BuildConfig
import com.sulistyolabs.made.ui.DetailsFilm
import com.sulistyolabs.made.R
import com.sulistyolabs.made.adapter.TvShowAdapter
import com.sulistyolabs.made.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.jetbrains.anko.support.v4.startActivity


class TvShowFragment : Fragment() {

    private lateinit var adapter: TvShowAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(MainViewModel::class.java)

        initData()

        mainViewModel.getTvShow().observe(this, Observer { tvShowItem ->
            if (tvShowItem != null) {
                adapter.setData(tvShowItem)
                showLoading(false)
            } else {
                Log.d("dec:", "Data kosong")
            }
        })
    }

    private fun initData() {
        val apiKey: String = BuildConfig.API_KEY
        val lang: String = resources.getString(R.string.lang)

        mainViewModel.setTvShow(apiKey, lang)
        showLoading(true)

        adapter = TvShowAdapter(requireContext()) {
            startActivity<DetailsFilm>("tvshow" to it)
        }

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_tv_show?.layoutManager = layoutManager
        rv_tv_show?.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar2.visibility = View.VISIBLE
        } else {
            progressBar2.visibility = View.GONE
        }
    }
}
