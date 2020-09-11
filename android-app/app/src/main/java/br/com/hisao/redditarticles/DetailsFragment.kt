package br.com.hisao.redditarticles

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args = arguments?.let { DetailsFragmentArgs.fromBundle(it) }
        return inflater.inflate(R.layout.fragment_details, container, false)
    }
}