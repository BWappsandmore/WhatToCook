package at.bwappsandmore.whattocook.di

import at.bwappsandmore.whattocook.ui.fish.fragment.FishFragment
import at.bwappsandmore.whattocook.ui.mealplan.fragment.MealPlanFragment
import at.bwappsandmore.whattocook.ui.noodle.fragment.NoodleFragment
import at.bwappsandmore.whattocook.ui.potato.fragment.PotatoFragment
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun injectFragment(target: MealPlanFragment)
    fun injectFragment(target: NoodleFragment)
    fun injectFragment(target: FishFragment)
    fun injectFragment(target: PotatoFragment)
}