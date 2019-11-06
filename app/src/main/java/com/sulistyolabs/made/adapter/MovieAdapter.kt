package com.sulistyolabs.made.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sulistyolabs.made.R
import com.sulistyolabs.made.data.model.MovieItem
import kotlinx.android.synthetic.main.item_film.view.*

class MovieAdapter(private val context: Context,
                   private val listener: (MovieItem) -> Unit)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val mData = ArrayList<MovieItem>()

    fun setData(items: List<MovieItem>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewTye: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_film,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(mData[position], context, listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(movieItem: MovieItem, context: Context, listener: (MovieItem) -> Unit) {
            val imgLink = "https://image.tmdb.org/t/p/w185/"
            Glide.with(context).load(imgLink+movieItem.poster_path).into(itemView.img_film)
            itemView.tv_film_name.text = movieItem.title
            itemView.tv_film_quotes.text = movieItem.release_date

            itemView.setOnClickListener {
                listener(movieItem)
            }
        }
    }

}