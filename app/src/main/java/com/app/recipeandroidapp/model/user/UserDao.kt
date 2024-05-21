package com.app.recipeandroidapp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert
    fun insert(user: User): Long

    @Query("SELECT * FROM users WHERE username = :username")
    fun findUserByUsername(username: String): User?

    @Update
    fun update(user: User): Int

    @Query("Select * from users")
    fun getAll(): List<User>
}
