package com.vishalpvijayan.smyttens.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MessageViewModel : ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    init {
        _title.value = "Smytten"
        _message.value = "Hello!"
    }

}
