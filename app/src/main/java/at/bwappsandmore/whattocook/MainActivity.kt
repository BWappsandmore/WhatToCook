package at.bwappsandmore.whattocook

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import at.bwappsandmore.whattocook.base.BaseActivity
import at.bwappsandmore.whattocook.databinding.ActivityMainBinding
import at.bwappsandmore.whattocook.di.AppModule
import at.bwappsandmore.whattocook.di.DaggerAppComponent
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.ui.main.SectionsPagerAdapter
import at.bwappsandmore.whattocook.viewModel.MainActivityViewModel
import at.bwappsandmore.whattocook.viewModel.MainActivityViewModelImpl
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
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun inject() {
        DaggerAppComponent.builder().appModule(AppModule(application)).build().inject(this)
    }
}