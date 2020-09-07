package br.com.hisao.redditarticles

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import br.com.hisao.redditarticles.databinding.FragmentOverviewBinding
import com.android.example.github.vo.Status
import java.time.LocalDate


class OverviewFragment : Fragment() {

    private lateinit var viewBinding: FragmentOverviewBinding

    private val overviewViewModelFactory = OverviewViewModelFactory()

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this, overviewViewModelFactory).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.liveData.observe(viewLifecycleOwner) {
            if (it.status == Status.SUCCESS) {
                Log.d("REDDIT_ARTICLES", "onCreateView: SUCCESS")
            } else if (it.status == Status.ERROR) {
                Log.d("REDDIT_ARTICLES", "onCreateView: ERROR")
            } else if (it.status == Status.LOADING) {
                Log.d("REDDIT_ARTICLES", "onCreateView: LOADING")
            } else {
                Log.d("REDDIT_ARTICLES", "onCreateView: NOT RECOGNIZED")
            }
        }

        viewBinding = FragmentOverviewBinding.inflate(layoutInflater)
        viewBinding.somebutton.setOnClickListener {
            Toast.makeText(context, "vamosoooos.", Toast.LENGTH_LONG).show()
            view?.findNavController()?.navigate(R.id.detailsFragment)

        }

        return viewBinding.root
    }

}