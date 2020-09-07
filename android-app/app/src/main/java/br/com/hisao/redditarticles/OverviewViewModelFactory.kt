package br.com.hisao.redditarticles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class OverviewViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OverviewViewModel::class.java)) {
            return OverviewViewModel(RedditRepository(), "kotlin") as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}