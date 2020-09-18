package br.com.hisao.redditarticles.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.hisao.redditarticles.repository.RedditRepository
import br.com.hisao.redditarticles.model.Resource
import br.com.hisao.redditarticles.model.json.Article
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val redditRepository: RedditRepository
) : ViewModel() {

    val articleViewModelLiveData: LiveData<Resource<Article>>
        get() = redditRepository.articleRepositoryMutableLiveData

    fun fetchData(articleId: String) {
        redditRepository.getArticle(articleId)
    }
}


