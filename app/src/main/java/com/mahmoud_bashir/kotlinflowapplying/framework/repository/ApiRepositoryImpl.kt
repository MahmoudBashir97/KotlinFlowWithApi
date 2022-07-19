package com.mahmoud_bashir.kotlinflowapplying.framework.repository

import com.mahmoud_bashir.kotlinflowapplying.data.PostsResponse
import kotlinx.coroutines.flow.Flow

interface ApiRepositoryImpl {
    suspend fun getAllPosts():Flow<PostsResponse>
}