package br.com.hisao.redditarticles

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.hisao.redditarticles.db.ArticleDatabaseDao
import br.com.hisao.redditarticles.model.ArticleEntity
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
    val articleRepositoryMutableLiveData = MutableLiveData<Resource<Children>>()
    val articleListRepositoryMutableLiveData = MutableLiveData<Resource<List<Children>>>()

    fun getArticles(subject: String): LiveData<Resource<List<Children>>> {

        articleListRepositoryMutableLiveData.postValue(Resource.loading(null))

        coroutineScope.launch {
            val getNewsDefered = webservice.RETROFIT_SERVICE_RETROFIT.getArticles(subject)
            var exception: Exception? = null
            try {
                val result = getNewsDefered.await().data.children
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
                        val result = getListChildrenObject(getAllArticlesFromDatabase())
                        articleListRepositoryMutableLiveData.postValue(Resource.success(result))
                    }
                } catch (ex: Exception) {
                    articleListRepositoryMutableLiveData.postValue(Resource.error(ex.localizedMessage ?: "", null))
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
                articleRepositoryMutableLiveData.postValue(Resource.success(getChildrenObject(result)))
            } catch (ex: Exception) {
                Log.d("REDDIT_ARTICLES", "RedditRepository:getArticle: " + ex.localizedMessage)
                articleRepositoryMutableLiveData.postValue(Resource.error(ex.localizedMessage ?: "", null))
            }
        }
    }

    private suspend fun clearDatabase() {
        withContext(Dispatchers.IO) {
            articleDatabaseDao.clear()
        }
    }

    private suspend fun addInDatabase(list: List<Children>) {
        withContext(Dispatchers.IO) {
            for (i in list) {
                articleDatabaseDao.insert(getDatabaseObject(i))
            }
        }
    }

    private suspend fun getDatabaseCount(): Int {
        return withContext(Dispatchers.IO) {
            articleDatabaseDao.getArticlesCount()
        }
    }

    private suspend fun getAllArticlesFromDatabase(): List<ArticleEntity> {
        return withContext(Dispatchers.IO) {
            articleDatabaseDao.getAllArticles()
        }
    }

    private suspend fun getArticleFromDatabase(articleId: String): ArticleEntity {
        return withContext(Dispatchers.IO) {
            articleDatabaseDao.getArticle(articleId)
        }
    }

    private fun getListChildrenObject(input: List<ArticleEntity>): List<Children> {
        val list = ArrayList<Children>()
        for (i in input) {
            list.add(getChildrenObject(i))
        }
        return list
    }

    private fun getChildrenObject(articleEntity: ArticleEntity): Children {
        val dataX = DataX(
            articleEntity.selftext,
            articleEntity.title,
            articleEntity.id,
            articleEntity.thumbnail,
            null,
            null,
            null,
            false,
            null,
            0,
            false,
            null,
            null,
            false,
            0,
            null,
            0,
            false,
            null,
            false,
            null,
            null,
            null,
            0,
            0,
            null,
            null,
            false,
            null,
            null,
            false,
            false,
            null,
            null,
            null,
            false,
            0,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            false,
            null,
            0.0,
            null,
            0,
            null,
            null,
            null,
            false,
            null,
            null,
            null,
            null,
            null,
            false,
            false,
            false,
            false,
            false,
            null,
            null,
            false,
            false,
            false,
            false,
            null,
            false,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            false,
            null,
            null,
            null,
            null,
            false,
            null,
            false,
            null,
            false,
            null,
            null,
            null,
            false,
            null,
            null,
            0.0,
            0,
            0,
            false
        )
        return Children("", dataX)
    }

    private fun getDatabaseObject(children: Children): ArticleEntity {
        return ArticleEntity(
            children.data.selftext ?: "",
            children.data.title ?: "",
            children.data.id,
            children.data.thumbnail ?: ""
        )
    }
}







