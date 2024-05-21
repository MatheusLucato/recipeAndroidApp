package com.app.recipeandroidapp

import android.content.Context
import androidx.room.Room
import com.app.recipeandroidapp.model.*
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
        Room.databaseBuilder(app, AppDatabase::class.java, "app_database.sqlite")
            .allowMainThreadQueries()
            .build()

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

    @Singleton
    @Provides
    fun provideMealDao(db: AppDatabase) = db.mealDao()

    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao) = UserRepository(userDao)

    @Singleton
    @Provides
    fun provideMealRepository(mealDao: MealDao) = MealRepository(mealDao)
}
