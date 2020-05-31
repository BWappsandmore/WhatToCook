package at.bwappsandmore.whattocook.di

import at.bwappsandmore.whattocook.ui.fish.fragment.FishFragment
import at.bwappsandmore.whattocook.ui.mealplan.fragment.MealPlanFragment
import at.bwappsandmore.whattocook.ui.noodle.fragment.NoodleFragment
import at.bwappsandmore.whattocook.ui.nosidedish.fragment.NoSideDishFragment
import at.bwappsandmore.whattocook.ui.potato.fragment.PotatoFragment
import at.bwappsandmore.whattocook.ui.rice.fragment.RiceFragment
import at.bwappsandmore.whattocook.ui.stew.fragment.StewFragment
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun injectFragment(target: MealPlanFragment)
    fun injectFragment(target: NoodleFragment)
    fun injectFragment(target: FishFragment)
    fun injectFragment(target: PotatoFragment)
    fun injectFragment(target: RiceFragment)
    fun injectFragment(target: NoSideDishFragment)
    fun injectFragment(target: StewFragment)
}