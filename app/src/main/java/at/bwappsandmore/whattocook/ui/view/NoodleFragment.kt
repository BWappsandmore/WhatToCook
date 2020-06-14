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
import kotlinx.android.synthetic.main.fragment_noodle.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class NoodleFragment : BaseFragment<SharedViewModel>() {

    @Inject
    lateinit var repository: AppRepository

    private var noodleAdapter = FragmentAdapter({ item, actionId ->
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

    override fun getLayoutResource(): Int = R.layout.fragment_noodle
    override fun getViewModelClass(): Class<SharedViewModel> = SharedViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        noodleRv.apply {
            addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
            layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
            adapter = noodleAdapter
        }

        viewModel.getAllMeals(NOODLE.value).observe(viewLifecycleOwner, Observer { meals ->
            meals?.let {
                noodleAdapter.setMeals(it)
            }
            return@Observer
        })
    }
}