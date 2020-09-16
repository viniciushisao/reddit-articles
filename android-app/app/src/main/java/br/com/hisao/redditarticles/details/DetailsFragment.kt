package br.com.hisao.redditarticles.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import br.com.hisao.redditarticles.databinding.FragmentDetailsBinding
import br.com.hisao.redditarticles.model.Status


class DetailsFragment : Fragment() {
    private lateinit var dataBinding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("REDDIT_ARTICLES", "DetailsFragment:onCreateView: ")
        dataBinding = FragmentDetailsBinding.inflate(layoutInflater)
        val articleId = args.articleId

        val application = requireNotNull(this.activity).application
        val detailsViewModelFactory = DetailsViewModelFactory(application)

        val viewModel: DetailsViewModel by lazy {
            ViewModelProvider(this, detailsViewModelFactory).get(DetailsViewModel::class.java)
        }

        viewModel.articleViewModelLiveData.observe(viewLifecycleOwner) {
            if (it.status == Status.SUCCESS) {
                it.let {
                    dataBinding.articleSelfText.text = it.data?.selftext?.trim()
                    dataBinding.articleTitle.text = it.data?.title?.trim()
                }
            } else if (it.status == Status.ERROR) {
                //TODO
                Log.d("REDDIT_ARTICLES", "onCreateView: ERROR")
            } else if (it.status == Status.LOADING) {
                //TODO
                Log.d("REDDIT_ARTICLES", "onCreateView: LOADING")
            } else {
                //TODO
                Log.d("REDDIT_ARTICLES", "onCreateView: NOT RECOGNIZED")
            }
        }


        viewModel.fetchData(articleId)
        return dataBinding.root
    }
}