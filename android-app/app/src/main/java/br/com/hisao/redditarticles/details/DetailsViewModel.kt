package br.com.hisao.redditarticles.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.hisao.redditarticles.RedditRepository
import br.com.hisao.redditarticles.model.Resource
import br.com.hisao.redditarticles.model.json.Children
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    val redditRepository: RedditRepository
) : ViewModel() {

    val articleViewModelLiveData: LiveData<Resource<Children>>
        get() = redditRepository.articleRepositoryMutableLiveData

    fun fetchData(articleId: String) {
        redditRepository.getArticle(articleId)
    }
}


