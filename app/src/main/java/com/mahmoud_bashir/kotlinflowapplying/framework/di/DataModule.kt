package com.mahmoud_bashir.kotlinflowapplying.framework.di

import com.mahmoud_bashir.kotlinflowapplying.framework.repository.PostsRepository
import com.mahmoud_bashir.kotlinflowapplying.framework.retrofit.ApiServiceInterface
import com.mahmoud_bashir.kotlinflowapplying.framework.retrofit.RetrofitInstance
import com.mahmoud_bashir.kotlinflowapplying.presentation.main.MainViewModel
import com.mahmoud_bashir.kotlinflowapplying.utilities.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val dataModule= module{

    single {
        getRetrofitInstance()
    }
    single {
        getApiInterface(get())
    }

    single { RetrofitInstance() }

    single {
        PostsRepository(get())
    }


}

val viewModelMainModule= module {
    viewModel{
        MainViewModel(androidApplication(),get())
    }
}

fun getRetrofitInstance():Retrofit{
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(client)
        .build()
}

fun getApiInterface(retrofit: Retrofit):ApiServiceInterface = retrofit.create(ApiServiceInterface::class.java)
