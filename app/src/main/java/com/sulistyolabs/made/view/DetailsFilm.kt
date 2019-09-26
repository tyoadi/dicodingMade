package com.sulistyolabs.made.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.sulistyolabs.made.R
import com.sulistyolabs.made.model.MovieItem
import com.sulistyolabs.made.model.TvShowItem
import kotlinx.android.synthetic.main.activity_details_film.*
import kotlinx.android.synthetic.main.detail_item_layout.*

class DetailsFilm : AppCompatActivity() {

    private lateinit var itemMovie: MovieItem
    private lateinit var itemTvShow: TvShowItem

    companion object {
        val MOVIE = "movie"
        val TV_SHOW = "tvshow"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_film)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val keyIntent = intent.hasExtra(MOVIE)
        val imgLink = resources.getString(R.string.img_link)

        if (keyIntent) {
            itemMovie = intent.getParcelableExtra(MOVIE)
            tv_desc.text = resources.getString(R.string.releaseDate)
            tv_film_name.text = itemMovie.title
            tv_film_quotes_details.text = itemMovie.releaseDate
            tv_decription.text = itemMovie.overview

            Glide.with(this).load(imgLink+itemMovie.posterPath).into(img_details)

        } else {
            itemTvShow = intent.getParcelableExtra(TV_SHOW)
            tv_desc.text = resources.getString(R.string.releaseDate)
            tv_film_name.text = itemTvShow.originalName
            tv_film_quotes_details.text = itemTvShow.firstAirDate
            tv_decription.text = itemTvShow.overview

            Glide.with(this).load(imgLink+itemTvShow.posterPath).into(img_details)
        }





    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

