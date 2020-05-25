package at.bwappsandmore.whattocook.ui.potato.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.base.BaseFragment
import at.bwappsandmore.whattocook.databinding.FragmentPotatoBinding
import at.bwappsandmore.whattocook.di.AppModule
import at.bwappsandmore.whattocook.di.DaggerAppComponent
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.ui.potato.adapter.PotatoAdapter
import at.bwappsandmore.whattocook.ui.potato.viewModel.PotatoViewModel
import at.bwappsandmore.whattocook.ui.potato.viewModel.PotatoViewModelImpl
import kotlinx.android.synthetic.main.fragment_potato.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class PotatoFragment : BaseFragment<FragmentPotatoBinding, PotatoViewModel>() {

    @Inject
    lateinit var repository: AppRepository

    private var potatoAdapter = PotatoAdapter(
        { item ->

        }, { item ->

        }
    )

    override fun getLayoutResource(): Int = R.layout.fragment_potato
    override fun getViewModelClass(): Class<PotatoViewModel> = PotatoViewModel::class.java
    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return PotatoViewModelImpl(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.displayAllMeals()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        potatoRv.apply {
            addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
            layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
            adapter = potatoAdapter
        }
    }

    override fun injectFragment() {
        DaggerAppComponent.builder().appModule(activity?.application?.let { AppModule(it) }).build()
            .injectFragment(this)
    }
}