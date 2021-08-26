package com.mdbillalhossain1.mvvm_kotlin.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mdbillalhossain1.mvvm_kotlin.ChatData

class MainViewModel : ViewModel() {
    private val TAG: String? = this.javaClass.name
    var chatList = mutableListOf<ChatData>()
    private var chatLiveDataObject = MutableLiveData<List<ChatData>>()
    val chatLiveData : LiveData<List<ChatData>> get() = chatLiveDataObject

    fun addChat(chatData: ChatData){
        chatList.add(chatData)
        chatLiveDataObject.value = chatList
        Log.d(TAG, "addChat: updated")
    }
}