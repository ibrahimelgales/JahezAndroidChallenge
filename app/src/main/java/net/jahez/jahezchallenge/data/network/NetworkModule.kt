package net.jahez.jahezchallenge.data.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.jahez.jahezchallenge.BuildConfig
import net.jahez.jahezchallenge.data.ImpRepositoryRestaurants
import net.jahez.jahezchallenge.data.network.service.ApiService
import net.jahez.jahezchallenge.domain.repository.IRepositoryRestaurants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //region var
    private const val READ_TIMEOUT = 60
    private const val WRITE_TIMEOUT = 60
    private const val CONNECTION_TIMEOUT = 60
    private val CONNECTION_TIME_UNIT = TimeUnit.SECONDS
    //endregion

    private const val BASE_URL = "https://jahez-other-oniiphi8.s3.eu-central-1.amazonaws.com"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor {
            val requestBuilder = it.request().newBuilder()
            //hear you can add all headers you want by calling 'requestBuilder.addHeader(name ,  value)'
            it.proceed(requestBuilder.build())
        }
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        interceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(CONNECTION_TIMEOUT.toLong(), CONNECTION_TIME_UNIT)
            .readTimeout(READ_TIMEOUT.toLong(), CONNECTION_TIME_UNIT)
            .writeTimeout(WRITE_TIMEOUT.toLong(), CONNECTION_TIME_UNIT)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    internal fun provideRepository(apiService: ApiService): IRepositoryRestaurants{
        return ImpRepositoryRestaurants(apiService)
    }
}