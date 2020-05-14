package at.bwappsandmore.whattocook.di

import android.app.Application
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.room.AppDatabase
import at.bwappsandmore.whattocook.room.WhatToCookDao
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.GlobalScope

@Module
class AppModule(private val app: Application) {

    @Provides
    fun provideDB(): AppDatabase {
        return AppDatabase.getDatabase(app, GlobalScope)
    }

    @Provides
    fun provideDAO(app: AppDatabase): WhatToCookDao {
        return app.whatToCookDao()
    }

    @Provides
    fun provideAppRepository(dao: WhatToCookDao): AppRepository{
        return AppRepository(dao)
    }
}