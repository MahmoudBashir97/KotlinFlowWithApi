package com.mahmoud_bashir.kotlinflowapplying

import android.app.Application
import com.mahmoud_bashir.kotlinflowapplying.framework.di.dataModule
import com.mahmoud_bashir.kotlinflowapplying.framework.di.viewModelMainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                dataModule,
                viewModelMainModule
            )
        }
    }
}