package br.com.hisao.redditarticles

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.hisao.redditarticles.db.ArticleDatabaseDao
import br.com.hisao.redditarticles.model.Resource
import br.com.hisao.redditarticles.model.json.Children
import br.com.hisao.redditarticles.model.json.DataX
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

private var viewModelJob = Job()
private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

@Singleton
class RedditRepository @Inject constructor(
    private val articleDatabaseDao: ArticleDatabaseDao
) {
    private val webservice: RedditWebServiceApi = RedditWebServiceApi
    val articleRepositoryMutableLiveData = MutableLiveData<Resource<DataX>>()
    val articleListRepositoryMutableLiveData = MutableLiveData<Resource<List<DataX>>>()


    fun getArticles(subject: String): LiveData<Resource<List<DataX>>> {

        articleListRepositoryMutableLiveData.postValue(Resource.loading(null))

        coroutineScope.launch {
            val getNewsDefered = webservice.RETROFIT_SERVICE_RETROFIT.getArticles(subject)
            var exception: Exception? = null
            try {
                val result = getListDataX(getNewsDefered.await().data.children)
                articleListRepositoryMutableLiveData.postValue(Resource.success(result))
                clearDatabase()
                addInDatabase(result)
            } catch (e: Exception) {
                Log.d("REDDIT_ARTICLES", "getArticles: ERROR " + e.localizedMessage)
                exception = e
            }

            if (exception != null) {
                try {
                    if (getDatabaseCount() > 0) {
                        val result = getAllArticlesFromDatabase()
                        articleListRepositoryMutableLiveData.postValue(Resource.success(result))
                    }
                } catch (ex: Exception) {
                    articleListRepositoryMutableLiveData.postValue(
                        Resource.error(
                            ex.localizedMessage ?: "", null
                        )
                    )
                }
            }
        }
        return articleListRepositoryMutableLiveData
    }

    fun getArticle(articleId: String) {
        articleRepositoryMutableLiveData.postValue(Resource.loading(null))
        coroutineScope.launch {
            try {
                val result = getArticleFromDatabase(articleId)
                articleRepositoryMutableLiveData.postValue(Resource.success(result))
            } catch (ex: Exception) {
                Log.d("REDDIT_ARTICLES", "RedditRepository:getArticle: " + ex.localizedMessage)
                articleRepositoryMutableLiveData.postValue(
                    Resource.error(
                        ex.localizedMessage ?: "",
                        null
                    )
                )
            }
        }
    }

    private suspend fun clearDatabase() {
        withContext(Dispatchers.IO) {
            articleDatabaseDao.clear()
        }
    }

    private suspend fun addInDatabase(list: List<DataX>) {
        withContext(Dispatchers.IO) {
            for (i in list) {
                articleDatabaseDao.insert(i)
            }
        }
    }

    private suspend fun getDatabaseCount(): Int {
        return withContext(Dispatchers.IO) {
            articleDatabaseDao.getArticlesCount()
        }
    }

    private suspend fun getAllArticlesFromDatabase(): List<DataX> {
        return withContext(Dispatchers.IO) {
            articleDatabaseDao.getAllArticles()
        }
    }

    private suspend fun getArticleFromDatabase(articleId: String): DataX {
        return withContext(Dispatchers.IO) {
            articleDatabaseDao.getArticle(articleId)
        }
    }


    private fun getListDataX(list: List<Children>): List<DataX> {

        val ans: MutableList<DataX> = ArrayList()

        for (children in list) {

            ans.add(children.data)

        }
        return ans
    }

}







