package at.bwappsandmore.whattocook.ui.noodle.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.base.BaseFragment
import at.bwappsandmore.whattocook.databinding.FragmentNoodleBinding
import at.bwappsandmore.whattocook.di.AppModule
import at.bwappsandmore.whattocook.di.DaggerAppComponent
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.ui.noodle.viewModel.NoodleViewModel
import at.bwappsandmore.whattocook.ui.noodle.viewModel.NoodleViewModelImpl
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class NoodleFragment : BaseFragment<FragmentNoodleBinding, NoodleViewModel>() {

    @Inject
    lateinit var repository: AppRepository

    override fun getLayoutResource(): Int = R.layout.fragment_noodle
    override fun getViewModelClass(): Class<NoodleViewModel> = NoodleViewModel::class.java

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.NewInstanceFactory(){
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return NoodleViewModelImpl(repository) as T
            }
        }
    }

    override fun injectFragment() {
        DaggerAppComponent.builder().appModule(activity?.application?.let { AppModule(it) }).build().injectFragment(this)
    }
}