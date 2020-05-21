package at.bwappsandmore.whattocook.di

import at.bwappsandmore.whattocook.ui.mealplan.fragment.MealPlanFragment
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun injectFragment(target: MealPlanFragment)
}