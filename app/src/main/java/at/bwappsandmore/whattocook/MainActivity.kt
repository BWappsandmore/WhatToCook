package at.bwappsandmore.whattocook

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
import at.bwappsandmore.whattocook.adapter.PagerAdapter
import at.bwappsandmore.whattocook.base.BaseActivity
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.room.MealEntity
import at.bwappsandmore.whattocook.ui.viewmodel.SharedViewModel
import at.bwappsandmore.whattocook.ui.viewmodel.SharedViewModelImpl
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import javax.inject.Inject

class MainActivity : BaseActivity<SharedViewModel>() {

    @Inject
    lateinit var repository: AppRepository

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    var fabImgRes = R.drawable.ic_add_white_24dp

    override fun getLayoutResource(): Int = R.layout.activity_main
    override fun getViewModelClass(): Class<SharedViewModel> = SharedViewModel::class.java

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.NewInstanceFactory() {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SharedViewModelImpl(repository) as T
            }
        }
    }

    companion object {
        private const val DELTA_Y = 0.5F
        private const val ROTATION_Y_COEFFICIENT = 45F
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewPager.apply {
            adapter = PagerAdapter(supportFragmentManager, lifecycle)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            offscreenPageLimit = OFFSCREEN_PAGE_LIMIT_DEFAULT

            setPageTransformer { page, position ->
                with(page) {
                    pivotX = if (position < 0F) width.toFloat() else 0F
                    pivotY = height * DELTA_Y
                    rotationY = ROTATION_Y_COEFFICIENT * position
                }
            }
        }

        TabLayoutMediator(
            tabs,
            viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.icon = when (position) {
                    0 -> ContextCompat.getDrawable(this, R.drawable.ic_list_meals)
                    1 -> ContextCompat.getDrawable(this, R.drawable.ic_fish_meal)
                    2 -> ContextCompat.getDrawable(this, R.drawable.ic_noodles)
                    3 -> ContextCompat.getDrawable(this, R.drawable.ic_potatoe_solid)
                    4 -> ContextCompat.getDrawable(this, R.drawable.ic_rice)
                    5 -> ContextCompat.getDrawable(this, R.drawable.ic_pizza)
                    6 -> ContextCompat.getDrawable(this, R.drawable.ic_stew)
                    else -> throw Exception()
                }
            }).attach()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        mealNameEt.visibility = View.GONE
                        main_fab.visibility = View.GONE
                        altern_fab.visibility = View.VISIBLE
                    }
                    else -> {
                        mealNameEt.visibility = View.VISIBLE
                        main_fab.visibility = View.VISIBLE
                        altern_fab.visibility = View.GONE
                    }
                }
                super.onPageSelected(position)
            }
        })

        altern_fab.setOnClickListener {
            viewModel.generateMealPlan()
        }

        main_fab.setOnClickListener {
            mealNameEt?.text.toString().isNotBlank().apply {
                if (mealNameEt.text.toString().isNotBlank()) {
                    if (fabImgRes == R.drawable.ic_add_white_24dp)
                        viewModel.insertMeal(MealEntity(0, viewPager.currentItem, mealNameEt.text.toString()))
                    else {
                        viewModel.updateMeal(mealNameEt.text.toString(), viewPager.currentItem, sharedPreferences.getInt("mealId", 0))
                        sharedPreferences.edit { putInt("mealId", 0) }
                        fabImgRes = R.drawable.ic_add_white_24dp
                    }

                    mealNameEt.text.clear()
                    main_fab.setImageResource(R.drawable.ic_add_white_24dp)
                }
            }
            hideSoftKeyboard(this@MainActivity)
        }
    }

    override fun onBackPressed() {
        mealNameEt.text.clear()
        main_fab.setImageResource(R.drawable.ic_add_white_24dp)
        viewModel.getAllMeals(viewPager.currentItem)
        super.onBackPressed()
    }

    fun putIdToSharedPrefs(mealEntity: MealEntity) {
        sharedPreferences.edit { putInt("mealId", mealEntity.id) }
    }

}