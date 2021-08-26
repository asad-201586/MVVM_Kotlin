package com.mdbillalhossain1.mvvm_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mdbillalhossain1.mvvm_kotlin.databinding.ItemChatBinding

class ChatAdapter(
    private val context: Context,
    private val dataList:List<ChatData>
    ) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root) {
        var chatTV:TextView = binding.textChat
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(ItemChatBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val data = dataList[position]
        holder.chatTV.text = "${data.textMessage} : ${data.author}"
    }

    override fun getItemCount(): Int {
        if (dataList.isNotEmpty())
            return dataList.size
        return 0
    }
}