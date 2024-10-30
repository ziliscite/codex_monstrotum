package com.compose.dungeonsanddragons.di

import android.app.Application
import androidx.room.Room
import com.compose.dungeonsanddragons.data.local.room.Converters
import com.compose.dungeonsanddragons.data.local.room.MonsterDao
import com.compose.dungeonsanddragons.data.local.room.MonsterDatabase
import com.compose.dungeonsanddragons.data.remote.service.MonsterApi
import com.compose.dungeonsanddragons.domain.manager.LocalUserManager
import com.compose.dungeonsanddragons.domain.repository.MonsterRepository
import com.compose.dungeonsanddragons.domain.usecases.appentry.AppEntryUseCases
import com.compose.dungeonsanddragons.domain.usecases.appentry.ReadAppEntry
import com.compose.dungeonsanddragons.domain.usecases.appentry.SaveAppEntry
import com.compose.dungeonsanddragons.domain.usecases.monster.DeleteMonster
import com.compose.dungeonsanddragons.domain.usecases.monster.GetFavoriteMonsterByIndex
import com.compose.dungeonsanddragons.domain.usecases.monster.GetFavoriteMonsters
import com.compose.dungeonsanddragons.domain.usecases.monster.GetMonsters
import com.compose.dungeonsanddragons.domain.usecases.monster.GetMonsterByIndex
import com.compose.dungeonsanddragons.domain.usecases.monster.MonsterUseCases
import com.compose.dungeonsanddragons.domain.usecases.monster.SearchMonsters
import com.compose.dungeonsanddragons.domain.usecases.monster.UpsertMonster
import com.compose.dungeonsanddragons.util.Constants
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) : AppEntryUseCases {
        return AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
        )
    }

    @Provides
    @Singleton
    fun provideMonsterApi() : MonsterApi {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MonsterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMonsterUseCases(
        monsterRepository: MonsterRepository
    ) : MonsterUseCases {
        return MonsterUseCases(
            getMonsters = GetMonsters(monsterRepository),
            getMonsterByIndex = GetMonsterByIndex(monsterRepository),
            searchMonsters = SearchMonsters(monsterRepository),
            getFavoriteMonsters = GetFavoriteMonsters(monsterRepository),
            upsertMonster = UpsertMonster(monsterRepository),
            deleteMonster = DeleteMonster(monsterRepository),
            getFavoriteMonsterByIndex = GetFavoriteMonsterByIndex(monsterRepository)
        )
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideConverters(gson: Gson): Converters {
        return Converters(gson)
    }

    @Provides
    @Singleton
    fun provideMonsterDatabase(
        context: Application,
        converters: Converters
    ) : MonsterDatabase{
        return Room.databaseBuilder(
            context,
            MonsterDatabase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration().addTypeConverter(converters).build()
    }

    @Provides
    @Singleton
    fun provideMonsterDao(
        database: MonsterDatabase
    ) : MonsterDao {
        return database.monsterDao
    }
}
