package com.sulistyolabs.made


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.support.v4.startActivity

class MovieFragment : Fragment() {

    private lateinit var adapter: MovieAdapter
    private var items: MutableList<MovieModel> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initData()
    }

    private fun initData() {

        val name = resources.getStringArray(R.array.film_name)
        val desc = resources.getStringArray(R.array.film_desc)
        val quotes = resources.getStringArray(R.array.film_quotes)
        val img = resources.getStringArray(R.array.film_img)
        items.clear()
        for (i in name.indices) {
            items.add(MovieModel(name[i],desc[i],quotes[i],img[i]))
        }

        adapter = MovieAdapter(requireContext(), items) {
            startActivity<DetailsFilm>("item" to it)
        }
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_movie?.layoutManager = layoutManager
        rv_movie.adapter = adapter
        adapter.notifyDataSetChanged()

    }

}
