package com.sulistyolabs.made

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_film.view.*

class FilmAdapter(private val context: Context,
                  private val listFilm: List<FilmModel>)
    : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_film, null)

            Glide.with(context).load(listFilm.get(position).img)
                .apply(RequestOptions().override(55, 55))
                .into(view.img_film)
            view.tv_film_name.text = listFilm.get(position).name
            view.tv_film_quotes.text = listFilm.get(position).quote

        } else {
            view = convertView
        }
        return view
    }

    override fun getItem(position: Int): Any {
        return listFilm[position]
    }

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int = listFilm.size

}