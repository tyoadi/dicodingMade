package com.sulistyolabs.made

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details_film.*
import kotlinx.android.synthetic.main.detail_item_layout.*

class DetailsFilm : AppCompatActivity() {

    private lateinit var listItem: MovieModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_film)

        listItem = intent.getParcelableExtra("item")

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        tv_film_name.text = listItem.name
        tv_film_quotes_details.text = listItem.quote
        tv_decription.text = listItem.dec

        Glide.with(this).load(listItem.img).into(img_details)

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

