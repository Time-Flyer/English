package com.example.english.viewmodel

import com.example.english.entity.SituationSentenceBean
import com.example.english.entity.SituationSentenceItemBean

class SituationSentenceModel {

    private val list = mutableListOf(
        SituationSentenceItemBean(grade = 1, sum = 14),
        SituationSentenceItemBean(grade = 2, sum = 14),
        SituationSentenceItemBean(grade = 3, sum = 15),
        SituationSentenceItemBean(grade = 4, sum = 15),
        SituationSentenceItemBean(grade = 5, sum = 15),
        SituationSentenceItemBean(grade = 6, sum = 15)
    )
    private val data = SituationSentenceBean(list = list)

    fun getSituationSentence(callBack: ModelDataCallBack<SituationSentenceBean>) {
        callBack.onSuccess(data)
    }
}