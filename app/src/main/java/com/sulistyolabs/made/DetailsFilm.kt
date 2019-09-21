package com.sulistyolabs.made

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details_film.*
import kotlinx.android.synthetic.main.detail_item_layout.*

class DetailsFilm : AppCompatActivity() {

    private lateinit var itemMovie: MovieModel
    private lateinit var itemTvShow: TvShowModel

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

        if (keyIntent) {
            itemMovie = intent.getParcelableExtra(MOVIE)
            tv_desc.text = resources.getString(R.string.quote)
            tv_film_name.text = itemMovie.name
            tv_film_quotes_details.text = itemMovie.quote
            tv_decription.text = itemMovie.dec

            Glide.with(this).load(itemMovie.img).into(img_details)

        } else {
            itemTvShow = intent.getParcelableExtra(TV_SHOW)
            tv_desc.text = resources.getString(R.string.releaseDate)
            tv_film_name.text = itemTvShow.name
            tv_film_quotes_details.text = itemTvShow.release
            tv_decription.text = itemTvShow.dec

            Glide.with(this).load(itemTvShow.img).into(img_details)
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

