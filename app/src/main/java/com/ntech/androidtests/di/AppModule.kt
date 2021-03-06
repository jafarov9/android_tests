package com.ntech.androidtests.di

import android.content.Context
import androidx.room.Room
import com.ntech.androidtests.api.RetrofitApi
import com.ntech.androidtests.roomdb.ArtDatabase
import com.ntech.androidtests.util.Util
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
    fun injectRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            ArtDatabase::class.java,
            "ArtBookDB"
        ).build()

    @Singleton
    @Provides
    fun injectDao(database: ArtDatabase) =
        database.artDao()

    @Singleton
    @Provides
    fun injectRetrofitApi() : RetrofitApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Util.BASE_URL)
            .build()
            .create(RetrofitApi::class.java)
    }
}