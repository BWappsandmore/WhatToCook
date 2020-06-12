package at.bwappsandmore.whattocook.ui.nogarnish.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.bwappsandmore.whattocook.MainActivity
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.base.BaseFragment
import at.bwappsandmore.whattocook.enums.ActionType
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.ui.DeleteMealFragment
import at.bwappsandmore.whattocook.ui.nogarnish.adapter.NoGarnishAdapter
import at.bwappsandmore.whattocook.ui.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_nogarnish.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class NoGarnishFragment : BaseFragment<SharedViewModel>() {

    companion object {
        private const val nogarnish = 5
    }

    @Inject
    lateinit var repository: AppRepository

    private var noGarnishAdapter = NoGarnishAdapter({ item, actionId ->
        when (actionId) {
            ActionType.EDIT -> Log.d("edit","edit")
            else -> {}
        }
    },{ item, actionId ->
        when (actionId) {
            ActionType.DELETE -> (activity as MainActivity).addFragment(R.id.smallContainer, DeleteMealFragment(), true)
            else -> {}
        }
    })

    override fun getLayoutResource(): Int = R.layout.fragment_nogarnish
    override fun getViewModelClass(): Class<SharedViewModel> = SharedViewModel::class.java



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        noSideDishRv.apply {
            addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
            layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
            adapter = noGarnishAdapter
        }

        viewModel.getAllMeals(NoGarnishFragment.nogarnish).observe(viewLifecycleOwner, Observer { meals ->
            meals?.let {
                noGarnishAdapter.setMeals(it)
            }

            return@Observer
        })
    }
}