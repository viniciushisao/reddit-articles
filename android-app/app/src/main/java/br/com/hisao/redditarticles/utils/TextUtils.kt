package br.com.hisao.redditarticles.utils

class TextUtils {
    companion object{
        fun isValidThumbnail(url: String?): Boolean {
            return !(url == null || url.isBlank() || url.isEmpty() || url == "self")
        }
    }
}