package br.com.hisao.redditarticles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainActivitySharedViewModel @Inject constructor(
) : ViewModel() {
    private val _actionBarTitleMutableLiveData = MutableLiveData<String>()
    val actionBarTitleMutableLiveData: LiveData<String>
        get() = _actionBarTitleMutableLiveData

    fun updateActionBarTitle(title: String) = _actionBarTitleMutableLiveData.postValue(title)

}
