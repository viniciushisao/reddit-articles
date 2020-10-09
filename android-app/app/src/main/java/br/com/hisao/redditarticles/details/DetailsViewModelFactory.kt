package br.com.hisao.redditarticles.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.hisao.redditarticles.repository.RedditRepository
import br.com.hisao.redditarticles.db.ArticleDatabase
import br.com.hisao.redditarticles.web.RedditWebServiceApi

class DetailsViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            val dataSource = ArticleDatabase.getInstance(application).dao
            val webservice: RedditWebServiceApi = RedditWebServiceApi
            return DetailsViewModel(RedditRepository(dataSource, webservice)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}