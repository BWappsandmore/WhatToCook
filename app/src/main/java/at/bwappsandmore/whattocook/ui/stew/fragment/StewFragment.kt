package at.bwappsandmore.whattocook.ui.stew.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.base.BaseFragment
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.ui.stew.adapter.StewAdapter
import at.bwappsandmore.whattocook.ui.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_stew.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class StewFragment : BaseFragment<SharedViewModel>() {

    companion object {
        private const val stew = 6
    }

    @Inject
    lateinit var repository: AppRepository

    private var stewAdapter = StewAdapter({ item ->

    }, { item ->

    })

    override fun getLayoutResource(): Int = R.layout.fragment_stew
    override fun getViewModelClass(): Class<SharedViewModel> = SharedViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        stewRV.apply {
            addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
            layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
            adapter = stewAdapter
        }

        viewModel.getAllMeals(StewFragment.stew).observe(viewLifecycleOwner, Observer { meals ->
            meals?.let {
                stewAdapter.setMeals(it)
            }

            return@Observer
        })
    }
}