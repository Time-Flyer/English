package com.example.english.viewmodel

interface ModelDataCallBack<T> {

    fun onSuccess(result: T)

    fun onFailure(errorLog: String, where: Int)
}