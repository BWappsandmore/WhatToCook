package at.bwappsandmore.whattocook.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity <T: BaseViewModel> : AppCompatActivity() {

    lateinit var viewModel: T

    abstract fun inject()
    abstract fun getLayoutResource(): Int
    abstract fun getViewModelClass(): Class<T>
    abstract fun getViewModelFactory(): ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()
        viewModel = ViewModelProvider(this, getViewModelFactory()).get(getViewModelClass())
    }
}