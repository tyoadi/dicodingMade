package com.sulistyolabs.made

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_film.view.*

class TvShowAdapter(private val context: Context,
                   private val itemMovie: List<TvShowModel>,
                   private val listener: (TvShowModel) -> Unit)
    : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(itemMovie[position], context, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewTye: Int) =
        TvShowAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_film, parent, false))

    override fun getItemCount(): Int = itemMovie.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(movieItem: TvShowModel, context: Context, listener: (TvShowModel) -> Unit) {
            Glide.with(context).load(movieItem.img).into(itemView.img_film)
            itemView.tv_film_name.text = movieItem.name

            itemView.setOnClickListener {
                listener(movieItem)
            }
        }
    }

}