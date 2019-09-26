package com.sulistyolabs.made.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sulistyolabs.made.R
import com.sulistyolabs.made.model.Movie
import com.sulistyolabs.made.model.MovieItem
import kotlinx.android.synthetic.main.item_film.view.*

class MovieAdapter(private val context: Context,
                   private val itemMovie: List<MovieItem>,
                   private val listener: (MovieItem) -> Unit)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewTye: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_film,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = itemMovie.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(itemMovie[position], context, listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(movieItem: MovieItem, context: Context, listener: (MovieItem) -> Unit) {

            val imgLink = context.resources.getString(R.string.img_link)
            Glide.with(context).load(imgLink+movieItem.posterPath).into(itemView.img_film)
            itemView.tv_film_name.text = movieItem.title
            itemView.tv_film_quotes.text = movieItem.releaseDate

            itemView.setOnClickListener {
                listener(movieItem)
            }
        }
    }

}