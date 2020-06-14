package at.bwappsandmore.whattocook.ui.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.bwappsandmore.whattocook.MainActivity
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.adapter.FragmentAdapter
import at.bwappsandmore.whattocook.base.BaseFragment
import at.bwappsandmore.whattocook.enums.ActionType
import at.bwappsandmore.whattocook.enums.MealType.*
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.ui.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_stew.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class StewFragment : BaseFragment<SharedViewModel>() {

    @Inject
    lateinit var repository: AppRepository

    private var stewAdapter = FragmentAdapter({ item, actionId ->
        when (actionId) {
            ActionType.EDIT -> Log.d("edit","edit")
            else -> {}
        }
    },{ item, actionId ->
        when (actionId) {
            ActionType.DELETE -> (activity as MainActivity).addFragment(R.id.smallContainer,
                DeleteMealFragment(), true)
            else -> {}
        }
    })

    override fun getLayoutResource(): Int = R.layout.fragment_stew
    override fun getViewModelClass(): Class<SharedViewModel> = SharedViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        stewRV.apply {
            addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
            layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
            adapter = stewAdapter
        }

        viewModel.getAllMeals(STEW.value).observe(viewLifecycleOwner, Observer { meals ->
            meals?.let {
                stewAdapter.setMeals(it)
            }
            return@Observer
        })
    }
}