package at.bwappsandmore.whattocook.di

import android.content.Context
import at.bwappsandmore.whattocook.WhatToCookApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class]
)

interface AppComponent : AndroidInjector<WhatToCookApp> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance appContext: Context): AppComponent
    }
}