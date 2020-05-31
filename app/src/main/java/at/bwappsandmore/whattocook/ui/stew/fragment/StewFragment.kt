package at.bwappsandmore.whattocook.ui.stew.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.base.BaseFragment
import at.bwappsandmore.whattocook.databinding.FragmentStewBinding
import at.bwappsandmore.whattocook.di.AppModule
import at.bwappsandmore.whattocook.di.DaggerAppComponent
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.ui.stew.adapter.StewAdapter
import at.bwappsandmore.whattocook.ui.stew.viewModel.StewViewModel
import at.bwappsandmore.whattocook.ui.stew.viewModel.StewViewModelImpl
import kotlinx.android.synthetic.main.fragment_stew.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class StewFragment : BaseFragment<FragmentStewBinding, StewViewModel>() {
    @Inject
    lateinit var repository: AppRepository

    private var stewAdapter = StewAdapter({ item ->

    }, { item ->

    })

    override fun getLayoutResource(): Int = R.layout.fragment_stew
    override fun getViewModelClass(): Class<StewViewModel> = StewViewModel::class.java

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return StewViewModelImpl(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.displayAllMeals()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        stewRV.apply {
            addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
            layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
            adapter = stewAdapter
        }
    }

    override fun injectFragment() {
        DaggerAppComponent.builder().appModule(activity?.application?.let { AppModule(it) }).build()
            .injectFragment(this)
    }
}