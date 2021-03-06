package br.com.hisao.redditarticles.overview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.room.paging.LimitOffsetDataSource
import br.com.hisao.redditarticles.MainActivitySharedViewModel
import br.com.hisao.redditarticles.R
import br.com.hisao.redditarticles.databinding.FragmentOverviewBinding
import br.com.hisao.redditarticles.model.Status
import br.com.hisao.redditarticles.util.isSearchTextValid

class OverviewFragment : Fragment() {

    private lateinit var dataBinding: FragmentOverviewBinding
    private val pageName = "Kotlin News"
    private val activitySharedViewModel: MainActivitySharedViewModel by activityViewModels()

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

        setHasOptionsMenu(true)

        activitySharedViewModel.updateActionBarTitle(pageName)

        dataBinding.articleList.adapter = adapter

        setListeners(dataBinding, viewModel)

        setObservables(viewModel, adapter)

        return dataBinding.root
    }

    private fun setListeners(binding: FragmentOverviewBinding, viewModel: OverviewViewModel) {
        dataBinding.searchButton.setOnClickListener {

            val searchString = dataBinding.searchText.text.toString()

            if (searchString.isSearchTextValid()) {
                viewModel.onSearchButtonClicked(searchString)
            } else {
                Toast.makeText(context, "Not Valid Text", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun setObservables(viewModel: OverviewViewModel, adapter: OverviewAdapter) {
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

        viewModel.searchSubject.observe(viewLifecycleOwner, Observer {
            viewModel.fetchArticleList(it)
        })
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.order_date -> {
                Log.d("REDDIT_ARTICLES", "OverviewFragment:onOptionsItemSelected: DATE ")
            }
            R.id.order_title -> {
                Log.d("REDDIT_ARTICLES", "OverviewFragment:onOptionsItemSelected: TITLE")
            }
        }

        return super.onOptionsItemSelected(item)
    }

}