package com.mahmoud_bashir.kotlinflowapplying.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.mahmoud_bashir.kotlinflowapplying.R
import com.mahmoud_bashir.kotlinflowapplying.data.PostsResponse
import com.mahmoud_bashir.kotlinflowapplying.data.PostsResponseItem
import com.mahmoud_bashir.kotlinflowapplying.presentation.main.MainViewModel
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private val mainViewModel by inject<MainViewModel>()
    var mlist:MutableLiveData<List<PostsResponseItem>> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.getAllPosts()

        lifecycleScope.launch(Dispatchers.Main){

            mainViewModel.data.collect{ response ->
                mlist.postValue(response)
                Toast.makeText(this@MainActivity,
                    "size : ${response.size}",Toast.LENGTH_SHORT).show()
                Log.d(TAG,"data deliveredSize : ${response.size}")
            }
        }

        mlist.observe(this) { list ->
                Log.d(TAG,"SZSSSSS : ${list.size}")
        }

    }


}