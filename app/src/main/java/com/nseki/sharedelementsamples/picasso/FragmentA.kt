package com.nseki.sharedelementsamples.picasso

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.nseki.sharedelementsamples.Navigator
import com.nseki.sharedelementsamples.databinding.FragmentABinding
import com.squareup.picasso.Picasso

class FragmentA : Fragment() {

    private lateinit var binding: FragmentABinding

    companion object {
        private const val KEY_IMAGE_RES_ID = "image_res"
        fun newInstance(@DrawableRes imageResId: Int): Fragment {
            return FragmentA().apply {
                arguments = bundleOf(KEY_IMAGE_RES_ID to imageResId)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentABinding.inflate(inflater, container, false)
        binding.imageA.setOnClickListener {
            (requireActivity() as Navigator).navigateToFragmentB(binding.imageA)
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Picasso.get()
            .load(getImagePath())
            .into(binding.imageA)
    }

    @DrawableRes
    private fun getImagePath(): Int {
        val args = arguments
        checkNotNull(args) { "Argument is null" }
        check(args.containsKey(KEY_IMAGE_RES_ID)) { "Argument does't have image res id" }
        return arguments!!.getInt(KEY_IMAGE_RES_ID)
    }
}
