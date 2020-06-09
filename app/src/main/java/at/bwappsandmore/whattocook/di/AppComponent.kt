package at.bwappsandmore.whattocook.di

import at.bwappsandmore.whattocook.MainActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(target: MainActivity)
}