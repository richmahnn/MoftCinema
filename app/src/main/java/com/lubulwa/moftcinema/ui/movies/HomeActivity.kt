package com.lubulwa.moftcinema.ui.movies

import android.os.Bundle
import com.lubulwa.moftcinema.R
import com.lubulwa.moftcinema.presentation.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


class HomeActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var pagerAdapter: MoftPagerAdapter

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewpager.adapter = pagerAdapter
        tabs.setupWithViewPager(viewpager)
    }

}
