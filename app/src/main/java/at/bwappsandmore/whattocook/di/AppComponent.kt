package at.bwappsandmore.whattocook.di

import at.bwappsandmore.whattocook.MainActivity
import at.bwappsandmore.whattocook.ui.mealplan.fragment.MealPlanFragment
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(target : MainActivity)
    fun injectFragment(target: MealPlanFragment)
}