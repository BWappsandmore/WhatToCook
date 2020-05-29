package at.bwappsandmore.whattocook.ui.nosidedish.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.base.BaseFragment
import at.bwappsandmore.whattocook.databinding.FragmentNosidedishBinding
import at.bwappsandmore.whattocook.di.AppModule
import at.bwappsandmore.whattocook.di.DaggerAppComponent
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.ui.nosidedish.adapter.NoSideDishAdapter
import at.bwappsandmore.whattocook.ui.nosidedish.viewModel.NoSideDishViewModel
import at.bwappsandmore.whattocook.ui.nosidedish.viewModel.NoSideDishViewModelImpl
import kotlinx.android.synthetic.main.fragment_nosidedish.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class NoSideDishFragment : BaseFragment<FragmentNosidedishBinding, NoSideDishViewModel>() {
    @Inject
    lateinit var repository: AppRepository

    private var noSideDishAdapter = NoSideDishAdapter(
        { item ->

        }, { item ->

        }
    )

    override fun getLayoutResource(): Int = R.layout.fragment_nosidedish
    override fun getViewModelClass(): Class<NoSideDishViewModel> = NoSideDishViewModel::class.java

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return NoSideDishViewModelImpl(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.displayAllMeals()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        noSideDishRv.apply {
            addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
            layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
            adapter = noSideDishAdapter
        }
    }

    override fun injectFragment() {
        DaggerAppComponent.builder().appModule(activity?.application?.let { AppModule(it) }).build()
            .injectFragment(this)
    }
}