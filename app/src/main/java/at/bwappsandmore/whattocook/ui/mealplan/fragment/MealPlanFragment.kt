package at.bwappsandmore.whattocook.ui.mealplan.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.base.BaseFragment
import at.bwappsandmore.whattocook.databinding.FragmentMealplanBinding
import at.bwappsandmore.whattocook.di.AppModule
import at.bwappsandmore.whattocook.di.DaggerAppComponent
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.ui.mealplan.adapter.MealPlanAdapter
import at.bwappsandmore.whattocook.ui.mealplan.viewModel.MealPlanViewModel
import at.bwappsandmore.whattocook.ui.mealplan.viewModel.MealPlanViewModelImpl
import kotlinx.android.synthetic.main.fragment_mealplan.*
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class MealPlanFragment : BaseFragment<FragmentMealplanBinding, MealPlanViewModel>() {

    @Inject
    lateinit var repository: AppRepository

    private var mealPlanAdapter = MealPlanAdapter({ item ->

    }, { item ->

    }
    )

    override fun getLayoutResource(): Int = R.layout.fragment_mealplan
    override fun getViewModelClass(): Class<MealPlanViewModel> = MealPlanViewModel::class.java

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MealPlanViewModelImpl(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.displayAllMeals()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mealPlanRv.apply {
            addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
            layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
            adapter = mealPlanAdapter
        }
    }

    override fun injectFragment() {
        DaggerAppComponent.builder().appModule(activity?.application?.let { AppModule(it) }).build()
            .injectFragment(this)
    }
}