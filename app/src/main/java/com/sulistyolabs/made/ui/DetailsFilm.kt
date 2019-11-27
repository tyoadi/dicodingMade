package com.sulistyolabs.made.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.sulistyolabs.made.R
import com.sulistyolabs.made.data.model.MovieItem
import com.sulistyolabs.made.data.model.TvItem
import com.sulistyolabs.made.data.source.local.MovieDB
import com.sulistyolabs.made.data.source.local.TvshowDB
import com.sulistyolabs.made.data.source.local.database
import kotlinx.android.synthetic.main.activity_details_film.*
import kotlinx.android.synthetic.main.detail_item_layout.*
import org.jetbrains.anko.db.StringParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailsFilm : AppCompatActivity() {

    private lateinit var itemMovie: MovieItem
    private lateinit var itemTvShow: TvItem

    private lateinit var mPresenter: DetailsPresenter

    private var isFavorite: Boolean = false
    private lateinit var id: String

    private var menuItem: Menu? = null

    val imgLink = "https://image.tmdb.org/t/p/w185/"
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

        mPresenter = DetailsPresenter()


        if (keyIntent) {
            itemMovie = intent.getParcelableExtra(MOVIE)
            tv_desc.text = resources.getString(R.string.quote)
            tv_film_name.text = itemMovie.title
            tv_film_quotes_details.text = itemMovie.release_date
            tv_decription.text = itemMovie.overview
            id = itemMovie.id.toString()
            Glide.with(this).load(imgLink+itemMovie.poster_path).into(img_details)

            favoriteStateMovie()

        } else {
            itemTvShow = intent.getParcelableExtra(TV_SHOW)
            tv_desc.text = resources.getString(R.string.releaseDate)
            tv_film_name.text = itemTvShow.name
            tv_film_quotes_details.text = itemTvShow.first_air_date
            tv_decription.text = itemTvShow.overview
            id = itemTvShow.id.toString()

            Glide.with(this).load(imgLink+itemTvShow.poster_path).into(img_details)

            favoriteStateTv()
        }

    }

    private fun favoriteStateMovie() {
        database.use {
            val result = select(MovieDB.TABLE_FAVORITE_MOVIE)
                .whereArgs("(MOVIE_ID = {id})",
                    "id" to id)
            val favorite = result.column(MovieDB.JSON_DATA).parseList(StringParser)
            isFavorite = favorite.isNotEmpty()

        }
        setFavorite()
    }

    private fun favoriteStateTv() {
        database.use {
            val result = select(TvshowDB.TABLE_FAVORITE_TV)
                .whereArgs("(TVSHOW_ID = {id})",
                    "id" to id)
            val favorite = result.column(TvshowDB.JSON_DATA).parseList(StringParser)
            isFavorite = favorite.isNotEmpty()

        }
        setFavorite()
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fav_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }

            R.id.add_to_favorite -> {
                if (isFavorite) {
                    if (intent.hasExtra(MOVIE)) {
                        mPresenter.removeFavoriteMovie(itemMovie.id.toString(), this)
                    } else {
                        mPresenter.removeFavoriteTvShow(itemTvShow.id.toString(), this)

                    }

                    toast("Remove from Favorite")

                } else {

                    if (intent.hasExtra(MOVIE)) {
                        mPresenter.addFavoriteMovie(itemMovie, this)

                    } else {
                        mPresenter.addFavoriteTvShow(itemTvShow, this)

                    }
                    toast("Added Favorite")
                }
                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}