package br.com.hisao.redditarticles.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.hisao.redditarticles.model.json.Article

@Dao
interface ArticleDatabaseDao {

    @Insert
    fun insert(data: Article)

    @Query("SELECT * from article WHERE article.id = :id")
    fun getArticle(id: String): Article

    @Query("DELETE FROM article")
    fun clear()

    @Query("SELECT * FROM article")
    fun getAllArticles(): List<Article>

    @Query("SELECT count(*) FROM article")
    fun getArticlesCount(): Int
}