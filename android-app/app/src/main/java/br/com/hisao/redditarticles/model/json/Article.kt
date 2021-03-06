package br.com.hisao.redditarticles.model.json


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")

data class Article(

    @ColumnInfo(name = "selftext")
    var selftext: String?,

    @ColumnInfo(name = "title")
    var title: String?,

    @PrimaryKey
    var id: String,

    @ColumnInfo(name = "thumbnail_uri")
    var thumbnail: String?

)