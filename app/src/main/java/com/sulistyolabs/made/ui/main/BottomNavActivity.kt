package com.sulistyolabs.made.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.sulistyolabs.made.R
import com.sulistyolabs.made.ui.favorite.FavoriteFragment
import com.sulistyolabs.made.ui.movie.MovieFragment
import com.sulistyolabs.made.ui.tvshow.TvShowFragment
import kotlinx.android.synthetic.main.activity_bottom_nav.*
import kotlinx.android.synthetic.main.home_main_activity.*

class BottomNavActivity : AppCompatActivity() {

    var movieFragment = MovieFragment()
    var tvFragment = TvShowFragment()
    var favFragment = FavoriteFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_main_activity)

        setSupportActionBar(toolbar)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {

                R.id.btn_movie -> {
                    loadFragment(movieFragment)
                    return@setOnNavigationItemSelectedListener true

                }

                R.id.btn_tv -> {
                    loadFragment(tvFragment)
                    return@setOnNavigationItemSelectedListener true

                }

                R.id.btn_fav -> {
                    loadFragment(favFragment)
                    return@setOnNavigationItemSelectedListener true

                }

            }
            return@setOnNavigationItemSelectedListener false
        }

        bottom_navigation.selectedItemId = R.id.btn_movie
    }

    private fun loadFragment(fragment : Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frag_container , fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.settings -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
