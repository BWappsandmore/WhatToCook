package at.bwappsandmore.whattocook.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import at.bwappsandmore.whattocook.ui.view.FishFragment
import at.bwappsandmore.whattocook.ui.view.MealPlanFragment
import at.bwappsandmore.whattocook.ui.view.NoodleFragment
import at.bwappsandmore.whattocook.ui.view.NoGarnishFragment
import at.bwappsandmore.whattocook.ui.view.PotatoFragment
import at.bwappsandmore.whattocook.ui.view.RiceFragment
import at.bwappsandmore.whattocook.ui.view.StewFragment

class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val items = listOf(
        MealPlanFragment(),
        FishFragment(),
        NoodleFragment(),
        PotatoFragment(),
        RiceFragment(),
        NoGarnishFragment(),
        StewFragment()
    )

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> MealPlanFragment()
        1 -> FishFragment()
        2 -> NoodleFragment()
        3 -> PotatoFragment()
        4 -> RiceFragment()
        5 -> NoGarnishFragment()
        6 -> StewFragment()
        else -> throw RuntimeException()
    }
}
