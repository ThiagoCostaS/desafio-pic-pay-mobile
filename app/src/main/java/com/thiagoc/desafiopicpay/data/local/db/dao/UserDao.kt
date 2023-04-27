package com.thiagoc.desafiopicpay.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thiagoc.desafiopicpay.data.local.db.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<UserEntity>)
}