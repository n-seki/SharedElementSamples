package com.nseki.sharedelementsamples.navigation

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.nseki.sharedelementsamples.databinding.FragmentBBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class FragmentB : Fragment() {

    private var _binding: FragmentBBinding? = null
    private val binding: FragmentBBinding
        get() = requireNotNull(_binding) {
            "ViewBinding is null"
        }
    private val navArgs: FragmentBArgs by navArgs()

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
        _binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
    }

    override fun onStart() {
        super.onStart()
        Picasso.get()
            .load(navArgs.imageRes)
            .into(binding.imageB, object : Callback {
                override fun onSuccess() {
                    startPostponedEnterTransition()
                }
                override fun onError(e: Exception?) {
                    startPostponedEnterTransition()
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}