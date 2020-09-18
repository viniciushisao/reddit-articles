package br.com.hisao.redditarticles.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import br.com.hisao.redditarticles.MainActivity
import br.com.hisao.redditarticles.databinding.FragmentOverviewBinding
import br.com.hisao.redditarticles.model.Status

class OverviewFragment : Fragment() {

    private lateinit var dataBinding: FragmentOverviewBinding
    private val defaultWordToSearch = "kotlin"
    private val pageName = "Kotlin News"

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

        //TODO REFACTOR IT
        val mainAct: MainActivity = activity as MainActivity
        mainAct.setTitleSupportActionBar(pageName)

        dataBinding.articleList.adapter = adapter

        viewModel.articleListViewModelLiveData.observe(viewLifecycleOwner) {

            changeScreen(it.status)

            when (it.status) {
                Status.SUCCESS -> {
                    it.let {
                        adapter.submitList(it.data)
                    }
                }
                Status.ERROR -> {
                    //TODO
                }
                Status.LOADING -> {
                    //TODO
                }
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
        when (status) {
            Status.LOADING -> {
                dataBinding.articleList.visibility = View.GONE
                dataBinding.loadingcontainer.visibility = View.VISIBLE
                dataBinding.errorcontainer.visibility = View.GONE
            }
            Status.SUCCESS -> {
                dataBinding.articleList.visibility = View.VISIBLE
                dataBinding.loadingcontainer.visibility = View.GONE
                dataBinding.errorcontainer.visibility = View.GONE
            }
            Status.ERROR -> {
                dataBinding.articleList.visibility = View.GONE
                dataBinding.loadingcontainer.visibility = View.GONE
                dataBinding.errorcontainer.visibility = View.VISIBLE
            }
        }
    }

}