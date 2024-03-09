package com.prosecshane.android_lab3.di

import com.prosecshane.android_lab3.data.remote.LabClientImpl
import com.prosecshane.android_lab3.data.repository.LabRepositoryImpl
import com.prosecshane.android_lab3.domain.repository.LabClient
import com.prosecshane.android_lab3.domain.repository.LabRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {
    @Binds
    @Singleton
    abstract fun bindRepository(
        labRepositoryImpl: LabRepositoryImpl
    ): LabRepository

    @Binds
    @Singleton
    abstract fun bindLabClient(
        labClientImpl: LabClientImpl
    ): LabClient
}