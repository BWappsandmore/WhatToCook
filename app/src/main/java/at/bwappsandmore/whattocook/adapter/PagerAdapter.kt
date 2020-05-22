package at.bwappsandmore.whattocook.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import at.bwappsandmore.whattocook.ui.fish.fragment.FishFragment
import at.bwappsandmore.whattocook.ui.mealplan.fragment.MealPlanFragment
import at.bwappsandmore.whattocook.ui.noodle.fragment.NoodleFragment

class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val items = listOf(
        MealPlanFragment(), FishFragment(), NoodleFragment()
    )

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> MealPlanFragment()
        1 -> FishFragment()
        2 -> NoodleFragment()
        else -> throw RuntimeException()
    }
}
