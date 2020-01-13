package com.nseki.sharedelementsamples.picasso

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.nseki.sharedelementsamples.Navigator
import com.nseki.sharedelementsamples.R
import com.nseki.sharedelementsamples.databinding.ActivityPicassoBinding

class PicassoSampleActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityPicassoBinding

    companion object {
        @DrawableRes private const val IMAGE_RES_ID = R.drawable.ic_android

        fun newIntent(context: Context) =
            Intent(context, PicassoSampleActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPicassoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        supportFragmentManager.commit {
            add(binding.fragmentContainer.id, FragmentA.newInstance(IMAGE_RES_ID))
        }
    }

    override fun navigateToFragmentB(sharedElement: View) {
        supportFragmentManager.commit {
            replace(binding.fragmentContainer.id, FragmentB.newInstance(IMAGE_RES_ID))
            addSharedElement(sharedElement, sharedElement.transitionName)
        }
    }
}
