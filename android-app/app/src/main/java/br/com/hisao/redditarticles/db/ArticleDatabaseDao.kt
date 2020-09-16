package br.com.hisao.redditarticles.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.hisao.redditarticles.model.ArticleEntity

@Dao
interface ArticleDatabaseDao {

    @Insert
    fun insert(data: ArticleEntity)

    @Query("SELECT * from article WHERE article.id = :id")
    fun getArticle(id: String): ArticleEntity

    @Query("DELETE FROM article")
    fun clear()

    @Query("SELECT * FROM article")
    fun getAllArticles(): List<ArticleEntity>

    @Query("SELECT count(*) FROM article")
    fun getArticlesCount(): Int
}