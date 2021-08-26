package com.mdbillalhossain1.mvvm_kotlin.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mdbillalhossain1.mvvm_kotlin.ChatAdapter
import com.mdbillalhossain1.mvvm_kotlin.ChatData
import com.mdbillalhossain1.mvvm_kotlin.R
import com.mdbillalhossain1.mvvm_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG: String? = this.javaClass.name
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ChatAdapter
    lateinit var mainViewModel: MainViewModel
    private var dataList:ArrayList<ChatData> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)


        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.chatLiveData.observe(this, Observer {
            if (it.isNotEmpty()){
                dataList.clear()
                dataList = ArrayList(it)
                adapter = ChatAdapter(this,dataList)
                binding.recyclerview.adapter = adapter
            }
        })

        binding.buttonAddQuote.setOnClickListener {

            val textMessage = binding.edtQuote.text.toString()
            val textAuthor = binding.edtAuthor.text.toString()

            when {
                textMessage.isEmpty() -> showToast("Enter text message")
                textAuthor.isEmpty() -> showToast("Enter text author")
                else -> {
                    val chatModel = ChatData(textMessage, textAuthor)
                    mainViewModel.addChat(chatModel)
                    binding.edtQuote.setText("")
                    binding.edtAuthor.setText("")
                }
            }
        }
    }

    private fun showToast(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}