package com.nseki.sharedelementsamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nseki.sharedelementsamples.databinding.ActivityMainBinding
import com.nseki.sharedelementsamples.picasso.PicassoSampleActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.picasso.setOnClickListener {
            startActivity(PicassoSampleActivity.newIntent(this))
        }
    }
}
