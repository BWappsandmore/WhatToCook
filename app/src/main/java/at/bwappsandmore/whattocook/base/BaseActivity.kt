package at.bwappsandmore.whattocook.base

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import at.bwappsandmore.whattocook.room.ListMealsEntity
import at.bwappsandmore.whattocook.room.MealEntity
import at.bwappsandmore.whattocook.ui.view.DelCopyShareFragment
import at.bwappsandmore.whattocook.ui.view.DelCopyShareListFragment
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity <T: BaseViewModel> : DaggerAppCompatActivity() {

    lateinit var viewModel: T

    abstract fun getLayoutResource(): Int
    abstract fun getViewModelClass(): Class<T>
    abstract fun getViewModelFactory(): ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        viewModel = ViewModelProvider(this, getViewModelFactory()).get(getViewModelClass())
        lifecycle.addObserver(viewModel)
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

    fun getInstanceDelFragment(mealEntity: MealEntity, comesFrom: Int): DelCopyShareFragment {
        val fragment = DelCopyShareFragment()
        val bundle = Bundle()
        bundle.putParcelable("item", mealEntity)
        bundle.putInt("fromFragment", comesFrom)
        fragment.arguments = bundle
        return fragment
    }

    fun getInstanceDelFragment(meal: ListMealsEntity): DelCopyShareListFragment {
        val fragment = DelCopyShareListFragment()
        val bundle = Bundle()
        bundle.putParcelable("item", meal)
        fragment.arguments = bundle
        return fragment
    }
}