package at.bwappsandmore.whattocook.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import at.bwappsandmore.whattocook.ui.mealplan.fragment.MealPlanFragment

class CollectionPagerAdapter(activity: AppCompatActivity, private val itemsCount: Int) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = itemsCount

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MealPlanFragment()
            //1 -> return NoodleFragment()
            else -> {
                Fragment()
            }

        }
    }
}