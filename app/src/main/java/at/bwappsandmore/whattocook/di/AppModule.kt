package at.bwappsandmore.whattocook.di

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.room.AppDatabase
import at.bwappsandmore.whattocook.room.WhatToCookDao
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: Application) {

    @Provides
    fun provideDB(): AppDatabase {
        return AppDatabase.getDatabase(app)
    }

    @Provides
    fun provideDAO(app: AppDatabase): WhatToCookDao {
        return app.whatToCookDao()
    }

    @Provides
    fun provideAppRepository(dao: WhatToCookDao): AppRepository{
        return AppRepository(dao)
    }

    @Provides
    fun provideSharedPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(app)
    }
}