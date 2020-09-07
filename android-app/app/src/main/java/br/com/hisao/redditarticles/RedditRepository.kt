package br.com.hisao.redditarticles

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.hisao.redditarticles.model.json.RedditNews
import com.android.example.github.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

private var viewModelJob = Job()
private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


@Singleton
class RedditRepository @Inject constructor(
) {
    private val webservice: RedditWebServiceApi = RedditWebServiceApi
    fun getArticles(subject: String): LiveData<Resource<RedditNews>> {
        val liveData = MutableLiveData<Resource<RedditNews>>()
        liveData.postValue(Resource.loading(null))

        coroutineScope.launch {
            val getNewsDefered = webservice.RETROFIT_SERVICE_RETROFIT.getArticles(subject)
            try {
                val result = getNewsDefered.await()
                Log.d("REDDIT_ARTICLES", "getRedditNewsProperties: SUCCESS")
                liveData.postValue(Resource.success(result))
            } catch (e: Exception) {
                Log.d("REDDIT_ARTICLES", "getRedditNewsProperties: ERROR")
                liveData.postValue(Resource.error(e.localizedMessage ?: "", null))
            }
        }
        return liveData
    }
}







