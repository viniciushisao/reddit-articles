package br.com.hisao.redditarticles

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class OverviewViewModel @Inject constructor(
    redditRepository: RedditRepository,
    subject: String
) : ViewModel() {
    val liveData = redditRepository.getArticles(subject)
}

