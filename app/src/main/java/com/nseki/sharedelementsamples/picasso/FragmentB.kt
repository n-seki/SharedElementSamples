package com.nseki.sharedelementsamples.picasso

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.nseki.sharedelementsamples.databinding.FragmentBBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class FragmentB : Fragment() {

    private lateinit var binding: FragmentBBinding

    companion object {
        private const val KEY_IMAGE_RES_ID = "image_res"
        fun newInstance(@DrawableRes imageResId: Int): Fragment {
            return FragmentB().apply {
                arguments = bundleOf(KEY_IMAGE_RES_ID to imageResId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
    }

    override fun onStart() {
        super.onStart()
        Picasso.get()
            .load(getImagePath())
            .into(binding.imageB, object : Callback {
                override fun onSuccess() {
                    startPostponedEnterTransition()
                }
                override fun onError(e: Exception?) {
                    startPostponedEnterTransition()
                }
            })
    }

    @DrawableRes
    private fun getImagePath(): Int {
        val args = arguments
        checkNotNull(args) { "Argument is null" }
        check(args.containsKey(KEY_IMAGE_RES_ID)) { "Argument does't have image res id" }
        return args.getInt(KEY_IMAGE_RES_ID)
    }
}