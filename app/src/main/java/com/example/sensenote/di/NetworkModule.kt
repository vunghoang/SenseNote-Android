package com.example.sensenote.di

//import com.example.sensenote.data.remote.ApiService
import com.example.sensenote.data.local.TokenManager
import com.example.sensenote.data.remote.AuthApi
import com.example.sensenote.data.remote.BehaviorCategoryApi
import com.example.sensenote.data.remote.BehaviorLogApi
import com.example.sensenote.data.remote.LessonApi
import com.example.sensenote.data.remote.SeatAssignmentApi
import com.example.sensenote.data.remote.StudentApi
import com.example.sensenote.data.remote.TeachingContextApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "http://10.0.2.2:8080/api/"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        tokenManager: TokenManager,
        loggingInterceptor: HttpLoggingInterceptor // Thêm tham số này
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor) // QUAN TRỌNG: Thêm dòng này để hiện Log mạng
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val token = tokenManager.getToken()
                if (token != null) {
                    request.addHeader("Authorization", "Bearer $token")
                }
                chain.proceed(request.build())
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStudentApi(retrofit: Retrofit): StudentApi {
        return retrofit.create(StudentApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTeachingContextApi(retrofit: Retrofit): TeachingContextApi {
        return retrofit.create(TeachingContextApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLessonApi(retrofit: Retrofit): LessonApi = retrofit.create(LessonApi::class.java)

    @Provides
    @Singleton
    fun provideBehaviorCategoryApi(retrofit: Retrofit): BehaviorCategoryApi =
        retrofit.create(BehaviorCategoryApi::class.java)

    @Provides
    @Singleton
    fun provideBehaviorLogApi(retrofit: Retrofit): BehaviorLogApi =
        retrofit.create(BehaviorLogApi::class.java)

    @Provides
    @Singleton
    fun provideSeatAssignmentApi(retrofit: Retrofit): SeatAssignmentApi {
        return retrofit.create(SeatAssignmentApi::class.java)
    }
}