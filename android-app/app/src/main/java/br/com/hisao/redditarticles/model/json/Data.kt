package br.com.hisao.redditarticles.model.json


data class Data(
    val modhash: String?,
    val dist: Int?,
    val children: List<Children>,
    val after: String?,
    val before: Any?
)