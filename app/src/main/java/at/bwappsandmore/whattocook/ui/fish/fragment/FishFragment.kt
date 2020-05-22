package at.bwappsandmore.whattocook.ui.fish.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.base.BaseFragment
import at.bwappsandmore.whattocook.databinding.FragmentFishBinding
import at.bwappsandmore.whattocook.di.AppModule
import at.bwappsandmore.whattocook.di.DaggerAppComponent
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.ui.fish.adapter.FishAdapter
import at.bwappsandmore.whattocook.ui.fish.viewModel.FishViewModel
import at.bwappsandmore.whattocook.ui.fish.viewModel.FishViewModelImpl
import kotlinx.android.synthetic.main.fragment_fish.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class FishFragment : BaseFragment<FragmentFishBinding, FishViewModel>(){

    @Inject
    lateinit var repository: AppRepository

    private var fishAdapter = FishAdapter({ item ->

    }, { item ->

    })

    override fun getLayoutResource(): Int = R.layout.fragment_fish
    override fun getViewModelClass(): Class<FishViewModel> = FishViewModel::class.java

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.NewInstanceFactory(){
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return FishViewModelImpl(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.displayAllMeals()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fishRv.apply {
            addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
            layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
            adapter = fishAdapter
        }
    }

    override fun injectFragment() {
        DaggerAppComponent.builder().appModule(activity?.application?.let { AppModule(it) }).build().injectFragment(this)
    }


}