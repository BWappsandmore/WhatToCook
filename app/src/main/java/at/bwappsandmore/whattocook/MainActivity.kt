package at.bwappsandmore.whattocook

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import at.bwappsandmore.whattocook.adapter.PagerAdapter
import at.bwappsandmore.whattocook.base.BaseActivity
import at.bwappsandmore.whattocook.di.AppModule
import at.bwappsandmore.whattocook.di.DaggerAppComponent
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.room.MealEntity
import at.bwappsandmore.whattocook.ui.view.DelCopyShareFragment
import at.bwappsandmore.whattocook.ui.view.InsertFishMealFragment
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

        viewPager.adapter = PagerAdapter(supportFragmentManager, lifecycle)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        viewPager.setPageTransformer { page, position ->
            with(page) {
                pivotX = if (position < 0F) width.toFloat() else 0F
                pivotY = height * DELTA_Y
                rotationY = ROTATION_Y_COEFFICIENT * position
            }
        }

        TabLayoutMediator(
            tabs,
            viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.icon = when (position) {
                    0 -> getDrawable(R.drawable.ic_list_meals)
                    1 -> getDrawable(R.drawable.ic_fish_meal)
                    2 -> getDrawable(R.drawable.ic_noodles)
                    3 -> getDrawable(R.drawable.ic_potatoe_solid)
                    4 -> getDrawable(R.drawable.ic_rice)
                    5 -> getDrawable(R.drawable.ic_pizza)
                    6 -> getDrawable(R.drawable.ic_stew)
                    else -> throw Exception()
                }
            }).attach()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0, 1 -> {
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
            when (viewPager.currentItem) {
                1 -> {
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    val fragment = InsertFishMealFragment()
                    fragmentTransaction.add(R.id.container, fragment)
                    fragmentTransaction.commit()
                }
                else -> {
                }
            }

            main_fab.setOnClickListener {
                mealNameEt.text.toString().trim().isNotEmpty().apply {
                    if (mealNameEt.text.toString().trim() != "") {
                        if (fabImgRes == R.drawable.ic_add_white_24dp) {
                            viewModel.insertMeal(
                                MealEntity(
                                    0,
                                    viewPager.currentItem,
                                    mealNameEt.text.toString()
                                )
                            )
                        } else {
                            viewModel.updateMeal(
                                mealNameEt.text.toString(),
                                viewPager.currentItem,
                                sharedPreferences.getInt("mealId", 0)
                            )
                        }

                        mealNameEt.text.clear()
                        main_fab.setImageResource(R.drawable.ic_add_white_24dp)
                    }
                }
                hideSoftKeyboard(this)
            }
        }
    }

    override fun inject() {
        DaggerAppComponent.builder().appModule(AppModule(application)).build().inject(this)
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