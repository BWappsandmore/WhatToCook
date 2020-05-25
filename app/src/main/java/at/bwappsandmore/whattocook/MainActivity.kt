package at.bwappsandmore.whattocook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import at.bwappsandmore.whattocook.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager.adapter = PagerAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(
            tabs,
            viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.icon = when (position) {
                    0 -> getDrawable(R.drawable.ic_list_meals)
                    1 -> getDrawable(R.drawable.ic_fish_meal)
                    2 -> getDrawable(R.drawable.ic_noodles)
                    3 -> getDrawable(R.drawable.ic_potatoe_solid)
                    else -> throw Exception()
                }
            }).attach()
    }
}