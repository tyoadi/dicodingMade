package com.sulistyolabs.made

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import android.provider.Settings.ACTION_LOCALE_SETTINGS
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentAdapter: FragmentAdapter
    var movieFragment = MovieFragment()
    var tvshowFragment = TvShowFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)


        fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(movieFragment, resources.getString(R.string.movie))
        fragmentAdapter.addFragment(tvshowFragment, resources.getString(R.string.tvshow))
        pagerTab.adapter = fragmentAdapter
        tab.setupWithViewPager(pagerTab)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.settings -> {
                val mIntent = Intent(ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
