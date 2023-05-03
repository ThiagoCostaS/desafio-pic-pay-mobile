package com.thiagoc.desafiopicpay.presentation.ui

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import androidx.test.runner.AndroidJUnitRunner
import com.thiagoc.desafiopicpay.application.di.*
import com.thiagoc.desafiopicpay.core.ApiService
import com.thiagoc.desafiopicpay.data.remote.network.PicPayService
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit

class MockTestRunner() : AndroidJUnitRunner() {
    override fun onCreate(arguments: Bundle?) {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .permitAll()
                .build()
        )
        super.onCreate(arguments)
    }

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, AppApplicationTest::class.java.name, context)
    }
}

class AppApplicationTest : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AppApplicationTest)
            loadKoinModules(
                listOf(
                    retrofitModuleTest,
                    dataSourceModule,
                    repositoryModule,
                    useCasesModule,
                    viewModelModule,
                    roomModule
                )
            )
        }
    }

    private val retrofitModuleTest = module {
        single { ApiService.initRetrofit("http://127.0.0.1:9000") }
        single { get<Retrofit>().create(PicPayService::class.java) }
    }
}