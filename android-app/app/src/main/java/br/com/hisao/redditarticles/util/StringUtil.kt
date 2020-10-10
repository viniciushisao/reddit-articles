package br.com.hisao.redditarticles.util

fun String?.isValidThumbnailUrl(): Boolean =
    (this == null || this.isBlank() || this.isEmpty() || this == "self")

fun String?.isSearchTextValid(): Boolean =
    (this == null || this.isBlank() || this.isEmpty())