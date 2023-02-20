package com.example.flixster2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content,MovieFragment(this), null).commit()
        // try to use the same fragment transaction
        val fragmentTransaction2 = supportFragmentManager.beginTransaction()
        fragmentTransaction2.replace(R.id.content2, TvShowsFragment(this), null).commit()
    }
    }
