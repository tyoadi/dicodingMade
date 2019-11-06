package com.sulistyolabs.made.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sulistyolabs.made.R
import com.sulistyolabs.made.data.model.MovieItem
import com.sulistyolabs.made.data.model.TvItem
import kotlinx.android.synthetic.main.item_film.view.*

class TvShowAdapter(private val context: Context,
                    private val listener: (TvItem) -> Unit)
    : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private val mData = ArrayList<TvItem>()

    fun setData(items: List<TvItem>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(mData[position], context, listener)
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


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(tvShowItem: TvItem, context: Context, listener: (TvItem) -> Unit) {
            val imgLink = "https://image.tmdb.org/t/p/w185/"
            Glide.with(context).load(imgLink+tvShowItem.poster_path).into(itemView.img_film)
            itemView.tv_film_name.text = tvShowItem.name
            itemView.tv_film_quotes.text = tvShowItem.overview

            itemView.setOnClickListener {
                listener(tvShowItem)
            }
        }
    }

}