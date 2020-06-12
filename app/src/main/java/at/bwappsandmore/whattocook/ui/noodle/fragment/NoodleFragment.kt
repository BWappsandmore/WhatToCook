package at.bwappsandmore.whattocook.ui.noodle.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.base.BaseFragment
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.ui.noodle.adapter.NoodleAdapter
import at.bwappsandmore.whattocook.ui.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_noodle.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class NoodleFragment : BaseFragment<SharedViewModel>() {

    companion object {
        private const val noodle = 2
    }

    @Inject
    lateinit var repository: AppRepository

    private var noodleAdapter = NoodleAdapter(
        { item ->

        }, { item ->

        }
    )

    override fun getLayoutResource(): Int = R.layout.fragment_noodle
    override fun getViewModelClass(): Class<SharedViewModel> = SharedViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllMeals(noodle)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        noodleRv.apply {
            addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
            layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
            adapter = noodleAdapter
        }

        viewModel.getAllMeals(noodle).observe(viewLifecycleOwner, Observer { meals ->
            meals?.let {
                noodleAdapter.setMeals(it)
            }

            return@Observer
        })
    }
}