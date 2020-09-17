package br.com.hisao.redditarticles.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.hisao.redditarticles.RedditRepository
import br.com.hisao.redditarticles.model.Resource
import br.com.hisao.redditarticles.model.json.Article
import javax.inject.Inject

class OverviewViewModel @Inject constructor(
    private val redditRepository: RedditRepository
) : ViewModel() {

    val articleListViewModelLiveData: LiveData<Resource<List<Article>>>
        get() = redditRepository.articleListRepositoryMutableLiveData

    private val _navigateToArticleDetail = MutableLiveData<String>()
    val navigateToArticleDetail
        get() = _navigateToArticleDetail


    fun fetchArticleList(subject: String) {
        redditRepository.getArticles(subject)
    }

    fun onArticleListClicked(id: String) {
        _navigateToArticleDetail.value = id
    }

    fun navigatedToArticleDetail() {
        _navigateToArticleDetail.value = null
    }
}

