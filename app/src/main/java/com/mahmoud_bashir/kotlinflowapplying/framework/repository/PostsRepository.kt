package com.mahmoud_bashir.kotlinflowapplying.framework.repository

import com.mahmoud_bashir.kotlinflowapplying.data.PostsResponse
import com.mahmoud_bashir.kotlinflowapplying.framework.retrofit.ApiServiceInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PostsRepository(private val apiServ:ApiServiceInterface):ApiRepositoryImpl {
    override suspend fun getAllPosts(): Flow<PostsResponse>
    = flow {
       val response =  apiServ.getAllPosts()
        emit(response)
    }.flowOn(Dispatchers.IO)
}