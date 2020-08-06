package at.bwappsandmore.whattocook.ui.view

import android.os.Bundle
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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_potato.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class PotatoFragment : BaseFragment<SharedViewModel>() {

    @Inject
    lateinit var repository: AppRepository

    private var potatoAdapter = FragmentAdapter({ item, actionId ->
        when (actionId) {
            ActionType.EDIT -> {
                (activity as MainActivity).mealNameEt.setText(item.mealName)
                (activity as MainActivity).main_fab.setImageResource(R.drawable.ic_baseline_edit_24)
                (activity as MainActivity).fabImgRes = R.drawable.ic_baseline_edit_24
                (activity as MainActivity).putIdToSharedPrefs(item)
            }
            else -> {}
        }
    },{ item, actionId ->
        when (actionId) {
            ActionType.DELCOPY -> (activity as MainActivity).addFragment(R.id.smallContainer,
                (activity as MainActivity).getInstanceDelFragment(item, POTATO.value), true)
            else -> {}
        }
    })

    override fun getLayoutResource(): Int = R.layout.fragment_potato
    override fun getViewModelClass(): Class<SharedViewModel> = SharedViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        potatoRv.apply {
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = potatoAdapter
        }

        viewModel.getAllMeals(POTATO.value).observe(viewLifecycleOwner, Observer { meals ->
            meals?.let {
                potatoAdapter.replaceAll(it)
            }
            return@Observer
        })
    }
}