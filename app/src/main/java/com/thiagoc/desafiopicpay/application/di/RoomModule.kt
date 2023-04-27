package com.thiagoc.desafiopicpay.application.di

import androidx.room.Room
import com.thiagoc.desafiopicpay.data.db.AppDatabase
import com.thiagoc.desafiopicpay.data.local.db.dao.UserDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "my-db").build()
    }

    single<UserDao> {
        get<AppDatabase>().userDao()
    }
}