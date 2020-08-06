package at.bwappsandmore.whattocook.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
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
import kotlinx.android.synthetic.main.fragment_fish.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class FishFragment : BaseFragment<SharedViewModel>() {

    @Inject
    lateinit var repository: AppRepository

    private var fishAdapter = FragmentAdapter({ item, actionId ->
        when (actionId) {
            ActionType.EDIT -> Log.d("edit", "edit")
            else -> {
            }
        }
    }, { item, actionId ->
        when (actionId) {
            ActionType.DELCOPY -> (activity as MainActivity).addFragment(
                R.id.smallContainer,
                (activity as MainActivity).getInstanceDelFragment(item, FISH.value), true
            )
            else -> {
            }
        }
    })

    override fun getLayoutResource(): Int = R.layout.fragment_fish
    override fun getViewModelClass(): Class<SharedViewModel> = SharedViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fishRv.apply {
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = fishAdapter
        }
        viewModel.getAllMeals(FISH.value).observe(viewLifecycleOwner, Observer { meals ->
            meals?.let {
                fishAdapter.replaceAll(it)
            }
            return@Observer
        })

    }
}