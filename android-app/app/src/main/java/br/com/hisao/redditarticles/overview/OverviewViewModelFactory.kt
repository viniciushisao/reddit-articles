package br.com.hisao.redditarticles.overview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.hisao.redditarticles.RedditRepository
import br.com.hisao.redditarticles.db.ArticleDatabase

class OverviewViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OverviewViewModel::class.java)) {
            val dataSource = ArticleDatabase.getInstance(application).dao
            return OverviewViewModel(RedditRepository(dataSource)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}