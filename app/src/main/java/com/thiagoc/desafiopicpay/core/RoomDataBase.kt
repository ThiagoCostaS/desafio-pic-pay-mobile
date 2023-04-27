package com.thiagoc.desafiopicpay.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thiagoc.desafiopicpay.data.local.db.dao.UserDao
import com.thiagoc.desafiopicpay.data.local.db.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}