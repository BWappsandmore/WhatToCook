package at.bwappsandmore.whattocook

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import at.bwappsandmore.whattocook.adapter.CollectionPagerAdapter
import at.bwappsandmore.whattocook.base.BaseActivity
import at.bwappsandmore.whattocook.databinding.ActivityMainBinding
import at.bwappsandmore.whattocook.di.AppModule
import at.bwappsandmore.whattocook.di.DaggerAppComponent
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.viewModel.MainActivityViewModel
import at.bwappsandmore.whattocook.viewModel.MainActivityViewModelImpl
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    @Inject
    lateinit var repository: AppRepository

    override fun getLayoutResource(): Int = R.layout.activity_main
    override fun getViewModelClass(): Class<MainActivityViewModel> =
        MainActivityViewModel::class.java

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainActivityViewModelImpl(repository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = CollectionPagerAdapter(this, 7)
        viewPager.adapter = adapter
    }

    override fun inject() {
        DaggerAppComponent.builder().appModule(AppModule(application)).build().inject(this)
    }
}