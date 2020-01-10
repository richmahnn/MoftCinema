package com.lubulwa.moftcinema.ui.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lubulwa.moftcinema.R
import kotlinx.android.synthetic.main.activity_movies.*


class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        //setSupportActionBar(toolbar)

        val pagerAdapter = MoftPagerAdapter(this, supportFragmentManager)
        viewpager.adapter = pagerAdapter
        tabs.setupWithViewPager(viewpager)
    }

}
