package at.bwappsandmore.whattocook.ui.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.bwappsandmore.whattocook.MainActivity
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.adapter.ListMealsAdapter
import at.bwappsandmore.whattocook.base.BaseFragment
import at.bwappsandmore.whattocook.enums.ActionType
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.ui.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_mealplan.*
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class MealPlanFragment : BaseFragment<SharedViewModel>() {

    @Inject
    lateinit var repository: AppRepository

    private var mealPlanAdapter = ListMealsAdapter { item, actionId ->
        when (actionId) {
            ActionType.DELCOPY -> (activity as MainActivity).addFragment(
                R.id.smallContainer,
                (activity as MainActivity).getInstanceDelFragment(item), true)
            else -> {
            }
        }
    }

    override fun getLayoutResource(): Int = R.layout.fragment_mealplan
    override fun getViewModelClass(): Class<SharedViewModel> = SharedViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mealPlanRv.apply {
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = mealPlanAdapter
        }

        viewModel.getListMeals().observe(viewLifecycleOwner, Observer {
            mealPlanAdapter.replaceAll(it)
        })

    }
}