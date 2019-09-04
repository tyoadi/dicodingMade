package com.sulistyolabs.made

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_film.view.*

class MovieAdapter(private val context: Context,
                   private val itemMovie: List<MovieModel>,
                   private val listener: (MovieModel) -> Unit)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewTye: Int) =
        MovieAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_film, parent, false))

    override fun getItemCount(): Int = itemMovie.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(itemMovie[position], context, listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(movieItem: MovieModel, context: Context, listener: (MovieModel) -> Unit) {
            Glide.with(context).load(movieItem.img).into(itemView.img_film)
            itemView.tv_film_name.text = movieItem.name
            itemView.tv_film_quotes.text = movieItem.quote

            itemView.setOnClickListener {
                listener(movieItem)
            }
        }
    }

}