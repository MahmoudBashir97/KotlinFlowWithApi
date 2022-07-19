package com.mahmoud_bashir.kotlinflowapplying.framework.retrofit

import com.mahmoud_bashir.kotlinflowapplying.data.PostsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiServiceInterface {
    @GET("posts")
    suspend fun getAllPosts():PostsResponse
}