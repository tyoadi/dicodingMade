package com.sulistyolabs.made.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sulistyolabs.made.R
import com.sulistyolabs.made.model.TvShowItem
import kotlinx.android.synthetic.main.item_film.view.*

class TvShowAdapter(private val context: Context,
                    private val itemMovie: List<TvShowItem>,
                    private val listener: (TvShowItem) -> Unit)
    : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(itemMovie[position], context, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewTye: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_film,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = itemMovie.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(tvShowItem: TvShowItem, context: Context, listener: (TvShowItem) -> Unit) {
            val imgLink = context.resources.getString(R.string.img_link)
            Glide.with(context).load(imgLink+tvShowItem.posterPath).into(itemView.img_film)
            itemView.tv_film_name.text = tvShowItem.name
            itemView.tv_film_quotes.text = tvShowItem.firstAirDate

            itemView.setOnClickListener {
                listener(tvShowItem)
            }
        }
    }

}