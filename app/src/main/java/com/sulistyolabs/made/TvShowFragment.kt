package com.sulistyolabs.made


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.jetbrains.anko.support.v4.startActivity


class TvShowFragment : Fragment() {

    private lateinit var adapter: TvShowAdapter
    private var items: MutableList<TvShowModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initData()
    }

    private fun initData() {

        val name = resources.getStringArray(R.array.tv_show)
        val release = resources.getStringArray(R.array.rilis_date)
        val desc = resources.getStringArray(R.array.desc_tv_show)
        val img = resources.obtainTypedArray(R.array.img_tv_show)
        items.clear()

        for (i in name.indices) {
            items.add(TvShowModel(name[i],release[i],desc[i],img.getResourceId(i, 0)))
        }

        adapter = TvShowAdapter(requireContext(), items) {
            startActivity<DetailsFilm>("tvshow" to it)
        }
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_tv_show?.layoutManager = layoutManager
        rv_tv_show?.adapter = adapter
        adapter.notifyDataSetChanged()

    }
}
