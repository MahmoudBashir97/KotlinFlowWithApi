package com.mahmoud_bashir.kotlinflowapplying.presentation.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mahmoud_bashir.kotlinflowapplying.data.PostsResponse
import com.mahmoud_bashir.kotlinflowapplying.data.PostsResponseItem
import com.mahmoud_bashir.kotlinflowapplying.framework.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(app:Application, private val repo:PostsRepository):AndroidViewModel(app) {

    lateinit var data:Flow<PostsResponse>
    var mlist:MutableLiveData<List<PostsResponseItem>> = MutableLiveData()


    fun getAllPosts(){
        viewModelScope.launch {
           data = repo.getAllPosts()
               .catch { Log.e("error:","Error: ${it.message}") }
               .flowOn(Dispatchers.IO)

        }
    }
}