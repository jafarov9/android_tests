package com.ntech.androidtests.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ntech.androidtests.R
import com.ntech.androidtests.databinding.FragmentArtsBinding

class ArtFragment: Fragment(R.layout.fragment_arts) {

    private var fragmentBinding: FragmentArtsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentArtsBinding.bind(view)
        fragmentBinding = binding

        binding.fab.setOnClickListener {
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }
}