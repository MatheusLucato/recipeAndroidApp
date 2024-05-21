package com.app.recipeandroidapp

import android.content.Context
import androidx.room.Room
import com.app.recipeandroidapp.model.UserDao
import com.app.recipeandroidapp.model.UserDatabase
import com.app.recipeandroidapp.model.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, UserDatabase::class.java,
            "user.sqlite")
            .allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideDao(db: UserDatabase) = db.userDao()

    @Singleton
    @Provides
    fun provideRepository(dao: UserDao) = UserRepository(dao)
}