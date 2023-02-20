package com.example.flixster2

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

private const val TAG = "TvShowsActivity"

class TvShowsActivity : AppCompatActivity() {
    private lateinit var tvShowsImageView: ImageView
    private lateinit var tvShowsTitleView: TextView
    private lateinit var tvShowsOverView: TextView
    // private lateinit var popularityTextView: TextView
    // private lateinit var releaseDateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tv_shows)
        supportPostponeEnterTransition()
        // add back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tvShowsImageView = findViewById(R.id.tPoster)
        tvShowsTitleView = findViewById(R.id.tName)
        tvShowsOverView = findViewById(R.id.tOverview)

        val tvshow = intent.getSerializableExtra(TVSHOWS_EXTRA) as TvShows


        tvShowsTitleView.text = tvshow.tvName
        tvShowsOverView.text = tvshow.tvOverview


        val radius = 30
        Glide.with(this)
            .load(("https://image.tmdb.org/t/p/w500/"+tvshow.tvPoster_path))
            .centerInside()
            .transform(RoundedCorners(radius))
            .into(tvShowsImageView)
        startPostponedEnterTransition()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            supportFinishAfterTransition()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}