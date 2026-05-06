package com.example.sensenote.di

import android.content.Context
import androidx.room.Room
import com.example.sensenote.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "sense_note_db"
        ).build()
    }

    @Provides
    fun provideStudentDao(database: AppDatabase) = database.studentDao()

//    @Provides
//    fun provideBehaviorDao(database: AppDatabase) = database.behaviorDao()
}