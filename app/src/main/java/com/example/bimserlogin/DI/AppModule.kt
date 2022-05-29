package com.example.bimserlogin.DI

import android.content.Context
import com.example.bimserlogin.repository.LoginRepository
import com.example.bimserlogin.service.LoginAPI
import com.example.bimserlogin.util.Constants.BASE_URL
import com.example.bimserlogin.repository.DataStorePreferenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLoginRepository(
        api: LoginAPI
    ) = LoginRepository(api)

    @Singleton
    @Provides
    fun provideLoginApi(): LoginAPI {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(LoginAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideDataStorePreferenceRepository(@ApplicationContext appContext: Context)
: DataStorePreferenceRepository = DataStorePreferenceRepository(appContext)
}