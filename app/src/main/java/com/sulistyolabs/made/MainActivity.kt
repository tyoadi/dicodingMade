package com.sulistyolabs.made

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private var items: MutableList<FilmModel> = mutableListOf()
    private lateinit var listFilm: ArrayList<FilmModel>
    private lateinit var filmAdapter: FilmAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
        filmAdapter = FilmAdapter(this, items)
        lv_film?.adapter = filmAdapter
        lv_film?.setOnItemClickListener { parent, view, position, id ->
            //val intent = Intent(this, DetailsFilm::class.java)
           //intent.putParcelableArrayListExtra("items", listFilm.get(position))
            //startActivity(intent)
            startActivity<DetailsFilm>("items" to items.get(position))
        }
    }

    private fun loadData() {
        val name = resources.getStringArray(R.array.film_name)
        val desc = resources.getStringArray(R.array.film_desc)
        val quotes = resources.getStringArray(R.array.film_quotes)
        val img = resources.getStringArray(R.array.film_img)
        items.clear()
        for (i in name.indices) {
            items.add(FilmModel(name[i],desc[i],quotes[i],img[i]))
        }
    }
}
