package br.com.hisao.redditarticles

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    redditRepository: RedditRepository,
    articleId: String
) : ViewModel() {

}