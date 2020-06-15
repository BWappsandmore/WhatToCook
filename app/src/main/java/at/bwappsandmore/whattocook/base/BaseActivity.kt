package at.bwappsandmore.whattocook.base

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity <T: BaseViewModel> : AppCompatActivity() {

    lateinit var viewModel: T

    abstract fun inject()
    abstract fun getLayoutResource(): Int
    abstract fun getViewModelClass(): Class<T>
    abstract fun getViewModelFactory(): ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        inject()
        viewModel = ViewModelProvider(this, getViewModelFactory()).get(getViewModelClass())
    }

    fun addFragment(@IdRes layoutId: Int, fragment: Fragment, backStack: Boolean = false) {
        val transfer = supportFragmentManager.beginTransaction()
            .add(layoutId, fragment)
        if (backStack) {
            transfer.addToBackStack(fragment.tag)
        }
        transfer.commit()
    }

    fun hideSoftKeyboard(activity: Activity?) {
        val inputMethodManager: InputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
    }

    fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}