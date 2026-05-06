package com.example.sensenote.di

import com.example.sensenote.data.repository.*
import com.example.sensenote.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindStudentRepository(impl: StudentRepositoryImpl): StudentRepository

    @Binds
    @Singleton
    abstract fun bindBehaviorRepository(impl: BehaviorRepositoryImpl): BehaviorRepository

    @Binds
    @Singleton
    abstract fun bindTeachingContextRepository(impl: TeachingContextRepositoryImpl): TeachingContextRepository

    @Binds
    @Singleton
    abstract fun bindClassRepository(
        classRepositoryImpl: ClassRepositoryImpl
    ): ClassRepository
}