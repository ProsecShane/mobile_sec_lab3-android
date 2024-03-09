package com.prosecshane.android_lab3.di

import android.app.Application
import androidx.room.Room
import com.prosecshane.android_lab3.data.local.DBConstants
import com.prosecshane.android_lab3.data.local.LabDao
import com.prosecshane.android_lab3.data.local.LabDatabase
import com.prosecshane.android_lab3.data.remote.APIConstants
import com.prosecshane.android_lab3.data.remote.LabApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvidesModule {
    @Provides
    @Singleton
    fun provideApi(): LabApi {
        return Retrofit.Builder()
                .baseUrl(APIConstants.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LabApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDao(appContext: Application): LabDao {
        return Room.databaseBuilder(
            context = appContext,
            klass = LabDatabase::class.java,
            name = DBConstants.fileName,
        ).build().dao()
    }
}
