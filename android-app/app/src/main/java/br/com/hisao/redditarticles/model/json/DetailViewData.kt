package br.com.hisao.redditarticles.model.json

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailViewData(val title:String?,
                          val author:String?,
                          val upVote:String?,
                         val url:String?,
                          val selfText:String?):Parcelable{

}