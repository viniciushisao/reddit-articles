package br.com.hisao.redditarticles.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import br.com.hisao.redditarticles.RedditRepository
import br.com.hisao.redditarticles.model.Resource
import br.com.hisao.redditarticles.model.json.Children
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    val redditRepository: RedditRepository
) : ViewModel() {

    val articleLiveData: LiveData<Resource<Children>>
        get() = redditRepository.articleLiveData


    fun fetchData(articleId: String) {
        redditRepository.getArticle(articleId)
    }


}


