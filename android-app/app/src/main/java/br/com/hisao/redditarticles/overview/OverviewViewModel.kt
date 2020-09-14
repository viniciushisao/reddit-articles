package br.com.hisao.redditarticles.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.hisao.redditarticles.RedditRepository
import javax.inject.Inject

class OverviewViewModel @Inject constructor(
    redditRepository: RedditRepository,
    subject: String
) : ViewModel() {
    val liveData = redditRepository.getArticles(subject)

    private val _navigateToArticleDetail = MutableLiveData<String>()
    val navigateToArticleDetail
        get() = _navigateToArticleDetail

    fun onArticleListClicked(id: String){
        _navigateToArticleDetail.value = id
    }
    fun navigatedToArticleDetail() {
        _navigateToArticleDetail.value = null
    }
}
