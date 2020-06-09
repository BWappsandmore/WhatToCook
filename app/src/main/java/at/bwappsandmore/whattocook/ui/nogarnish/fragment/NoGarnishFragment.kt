package at.bwappsandmore.whattocook.ui.nogarnish.fragment

import android.os.Bundle
import android.view.View

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.base.BaseFragment
import at.bwappsandmore.whattocook.databinding.FragmentNogarnishBinding
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.ui.nogarnish.adapter.NoSideDishAdapter
import at.bwappsandmore.whattocook.ui.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_nogarnish.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class NoGarnishFragment : BaseFragment<FragmentNogarnishBinding, SharedViewModel>() {
    @Inject
    lateinit var repository: AppRepository

    private var noSideDishAdapter = NoSideDishAdapter(
        { item ->

        }, { item ->

        }
    )

    override fun getLayoutResource(): Int = R.layout.fragment_nogarnish
    override fun getViewModelClass(): Class<SharedViewModel> = SharedViewModel::class.java



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.viewModel = viewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        noSideDishRv.apply {
            addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
            layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
            adapter = noSideDishAdapter
        }
    }
}