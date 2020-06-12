package at.bwappsandmore.whattocook.ui.rice.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.base.BaseFragment
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.ui.rice.adapter.RiceAdapter
import at.bwappsandmore.whattocook.ui.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_rice.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class RiceFragment : BaseFragment<SharedViewModel>() {

    companion object {
        private const val rice = 4
    }

    @Inject
    lateinit var repository: AppRepository

    private var riceAdapter = RiceAdapter({ item ->

    }, { item ->

    })

    override fun getLayoutResource(): Int = R.layout.fragment_rice
    override fun getViewModelClass(): Class<SharedViewModel> = SharedViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        riceRV.apply {
            addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
            layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
            adapter = riceAdapter
        }

        viewModel.getAllMeals(RiceFragment.rice).observe(viewLifecycleOwner, Observer { meals ->
            meals?.let {
                riceAdapter.setMeals(it)
            }

            return@Observer
        })
    }
}