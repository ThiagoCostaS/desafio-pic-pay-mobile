package com.thiagoc.desafiopicpay.application
import android.app.Application
import com.thiagoc.desafiopicpay.application.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            loadKoinModules(
                listOf(
                    retrofitModule,
                    dataSourceModule,
                    repositoryModule,
                    useCasesModule,
                    viewModelModule,
                    roomModule
                )
            )
        }
    }
}