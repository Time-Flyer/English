package com.example.english.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.english.entity.SituationSentenceBean

object LiveDataVMFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return SituationSentenceViewModel() as T
    }
}

class SituationSentenceViewModel : ViewModel() {

    private val model = SituationSentenceModel()

    private val _items = MutableLiveData<SituationSentenceBean>()
    val items: LiveData<SituationSentenceBean>
        get() = _items

    fun getSituationSentence() {
        model.getSituationSentence(object : ModelDataCallBack<SituationSentenceBean> {
            override fun onSuccess(result: SituationSentenceBean) {
                _items.value = result
            }

            override fun onFailure(errorLog: String, where: Int) {

            }
        })
    }
}