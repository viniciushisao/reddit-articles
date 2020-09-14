package br.com.hisao.redditarticles.overview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import br.com.hisao.redditarticles.databinding.FragmentOverviewBinding
import com.android.example.github.vo.Status


class OverviewFragment : Fragment() {

    private lateinit var dataBinding: FragmentOverviewBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = FragmentOverviewBinding.inflate(layoutInflater)

        val application = requireNotNull(this.activity).application
        val overviewViewModelFactory = OverviewViewModelFactory(application)

        val viewModel: OverviewViewModel by lazy {
            ViewModelProvider(this, overviewViewModelFactory).get(OverviewViewModel::class.java)
        }


        val adapter = OverviewAdapter(ArticleOnClickListener {
            viewModel.onArticleListClicked(it)
        })
        dataBinding.articleList.adapter = adapter

        viewModel.liveData.observe(viewLifecycleOwner) {
            if (it.status == Status.SUCCESS) {
                it?.let {
                    adapter.submitList(it.data)
                }
                Log.d("REDDIT_ARTICLES", "onCreateView: SUCCESS")
            } else if (it.status == Status.ERROR) {
                Log.d("REDDIT_ARTICLES", "onCreateView: ERROR")
            } else if (it.status == Status.LOADING) {
                Log.d("REDDIT_ARTICLES", "onCreateView: LOADING")
            } else {
                Log.d("REDDIT_ARTICLES", "onCreateView: NOT RECOGNIZED")
            }
        }

        dataBinding.somebutton.setOnClickListener {
            Toast.makeText(context, "vamosoooos.", Toast.LENGTH_LONG).show()
        }
        viewModel.navigateToArticleDetail.observe(viewLifecycleOwner, Observer { id ->
            id?.let {
                view?.findNavController()
                    ?.navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailsFragment(it))
                viewModel.navigatedToArticleDetail()
            }
        })

        return dataBinding.root
    }

}