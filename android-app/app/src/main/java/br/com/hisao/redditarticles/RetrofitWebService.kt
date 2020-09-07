package br.com.hisao.redditarticles

import br.com.hisao.redditarticles.model.json.RedditNews
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


private const val BASE_URL = "https://www.reddit.com/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface RetrofitWebServiceInterface{
    @GET("r/{subject}/.json")
    fun getArticles(@Path("subject") subject: String): Deferred<RedditNews>
}

object RedditWebServiceApi{
    val RETROFIT_SERVICE_RETROFIT:RetrofitWebServiceInterface by lazy {
        retrofit.create(RetrofitWebServiceInterface::class.java)
    }
}