package at.bwappsandmore.whattocook.di

import at.bwappsandmore.whattocook.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    fun injectContactsActivity(): MainActivity
}