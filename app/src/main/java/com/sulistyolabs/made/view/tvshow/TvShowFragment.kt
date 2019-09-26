package com.sulistyolabs.made.view.tvshow


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sulistyolabs.made.R
import com.sulistyolabs.made.adapter.TvShowAdapter
import com.sulistyolabs.made.model.TvShowItem
import com.sulistyolabs.made.utils.invisible
import com.sulistyolabs.made.utils.visible
import com.sulistyolabs.made.view.DetailsFilm
import kotlinx.android.synthetic.main.error_loading.*
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.toast


class TvShowFragment : Fragment(), TvShowContract.View {


    private lateinit var adapter: TvShowAdapter
    private var items: MutableList<TvShowItem> = mutableListOf()
    private lateinit var mPresenter: TvShowPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mPresenter = TvShowPresenter(this)

        initData()
    }

    private fun initData() {

        val apiKey: String = resources.getString(R.string.api_key)
        val lang: String = resources.getString(R.string.languange)
        mPresenter.getDataTvShow(apiKey, lang)
        adapter = TvShowAdapter(requireContext(), items) {
            startActivity<DetailsFilm>("tvshow" to it)
        }
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_tv?.layoutManager = layoutManager
        rv_tv?.adapter = adapter
    }

    override fun showListTvShow(data: List<TvShowItem>) {
        items.clear()
        data?.let { items.addAll(it) }
        adapter.notifyDataSetChanged()
    }

    override fun onEmpty() {
        rv_tv.invisible()
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
        rv_tv.invisible()
        progressBar.invisible()
        container_error.visible()
    }

    override fun onError(message: String) {
        requireContext().toast(message.toString())
        Log.e("error loading", message)
        rv_tv.invisible()
        progressBar.invisible()
        container_error.visible()
    }
}
