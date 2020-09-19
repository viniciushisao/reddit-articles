package br.com.hisao.redditarticles.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import br.com.hisao.redditarticles.MainActivitySharedViewModel
import br.com.hisao.redditarticles.databinding.FragmentDetailsBinding
import br.com.hisao.redditarticles.model.Status
import br.com.hisao.redditarticles.utils.TextUtils
import com.bumptech.glide.Glide


class DetailsFragment : Fragment() {
    private lateinit var dataBinding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val activitySharedViewModel: MainActivitySharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = FragmentDetailsBinding.inflate(layoutInflater)
        val articleId = args.articleId

        val application = requireNotNull(this.activity).application
        val detailsViewModelFactory = DetailsViewModelFactory(application)

        val viewModel: DetailsViewModel by lazy {
            ViewModelProvider(this, detailsViewModelFactory).get(DetailsViewModel::class.java)
        }


        viewModel.articleViewModelLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.let {
                        dataBinding.articleSelfText.text = it.data?.selftext?.trim()

                        val urlThumb = it.data?.thumbnail
                        if (TextUtils.isValidThumbnail(urlThumb)) {
                            dataBinding.thumb.visibility = View.VISIBLE
                            context?.let { it1 ->
                                Glide
                                    .with(it1)
                                    .load(urlThumb)
                                    .centerCrop()
                                    .placeholder(android.R.drawable.spinner_background)
                                    .into(dataBinding.thumb)
                            }
                        } else {
                            dataBinding.thumb.visibility = View.GONE
                        }

                        it.data?.title?.trim()
                            ?.let { title -> activitySharedViewModel.updateActionBarTitle(title) }

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


        viewModel.fetchData(articleId)
        return dataBinding.root
    }
}