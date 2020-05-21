package at.bwappsandmore.whattocook.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<E: ViewDataBinding, T: BaseViewModel>: Fragment() {
    lateinit var viewModel: T
    lateinit var dataBinding: E

    abstract fun getLayoutResource(): Int
    abstract fun getViewModelClass(): Class<T>
    abstract fun getViewModelFactory(): ViewModelProvider.Factory
    abstract fun injectFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            injectFragment()
            viewModel = ViewModelProvider(it, getViewModelFactory()).get(getViewModelClass())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), getLayoutResource(), container, false)
        lifecycle.addObserver(viewModel)
        return dataBinding.root
    }
}