package at.bwappsandmore.whattocook.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.room.AppDatabase
import at.bwappsandmore.whattocook.room.WhatToCookDao
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideDB(context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
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
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}