package com.nseki.sharedelementsamples.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nseki.sharedelementsamples.databinding.FragmentABinding
import com.squareup.picasso.Picasso

class FragmentA : Fragment() {

    private var _binding: FragmentABinding? = null
    private val binding: FragmentABinding
        get() = requireNotNull(_binding) {
            "ViewBinding is null"
        }
    private val navArgs: FragmentAArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentABinding.inflate(inflater, container, false)
        binding.imageA.setOnClickListener {
            val action = FragmentADirections.actionFragmentAToFragmentB(navArgs.imageRes)
            val extra = FragmentNavigatorExtras(binding.imageA to binding.imageA.transitionName)
            findNavController().navigate(action, extra)
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Picasso.get()
            .load(navArgs.imageRes)
            .into(binding.imageA)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}