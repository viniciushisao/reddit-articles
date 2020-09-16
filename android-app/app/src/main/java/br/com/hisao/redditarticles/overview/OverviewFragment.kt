package br.com.hisao.redditarticles.overview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import br.com.hisao.redditarticles.databinding.FragmentOverviewBinding
import br.com.hisao.redditarticles.model.Status

class OverviewFragment : Fragment() {

    private lateinit var dataBinding: FragmentOverviewBinding
    private val defaultWordToSearch = "kotlin"

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

        viewModel.articleListViewModelLiveData.observe(viewLifecycleOwner) {

            changeScreen(it.status)

            if (it.status == Status.SUCCESS) {
                it.let {
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

        viewModel.navigateToArticleDetail.observe(viewLifecycleOwner, Observer { id ->
            id?.let {
                view?.findNavController()
                    ?.navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailsFragment(it))
                viewModel.navigatedToArticleDetail()
            }
        })

        viewModel.fetchArticleList(defaultWordToSearch)
        return dataBinding.root
    }

    private fun changeScreen(status: Status) {
        if (status == Status.LOADING) {
            dataBinding.articleList.visibility = View.GONE
            dataBinding.loadingcontainer.visibility = View.VISIBLE
            dataBinding.errorcontainer.visibility = View.GONE
        } else if (status == Status.SUCCESS) {
            dataBinding.articleList.visibility = View.VISIBLE
            dataBinding.loadingcontainer.visibility = View.GONE
            dataBinding.errorcontainer.visibility = View.GONE
        } else if (status == Status.ERROR) {
            dataBinding.articleList.visibility = View.GONE
            dataBinding.loadingcontainer.visibility = View.GONE
            dataBinding.errorcontainer.visibility = View.VISIBLE
        }
    }

}