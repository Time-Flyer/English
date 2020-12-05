package com.example.english.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.english.entity.GrammarVideoBean

class GrammarVideoViewModel(list: List<GrammarVideoBean>): ViewModel() {
    private val _items = MutableLiveData(list)
    val items: LiveData<List<GrammarVideoBean>> = _items
}