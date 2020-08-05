package at.bwappsandmore.whattocook.ui.view

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import at.bwappsandmore.whattocook.MainActivity
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.base.BaseFragment
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.ui.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_insert_fishmeal.*
import javax.inject.Inject

class InsertFishMealFragment : BaseFragment<SharedViewModel>() {
    @Inject
    lateinit var repository: AppRepository

    override fun getLayoutResource(): Int = R.layout.fragment_insert_fishmeal
    override fun getViewModelClass(): Class<SharedViewModel> = SharedViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showSoftKeyboard(fishMealNameEt)
    }

    private fun showSoftKeyboard(v: View) {
        val inputMethodManager: InputMethodManager = (activity as MainActivity).getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        v.requestFocus()
        inputMethodManager.showSoftInput(v,0)
    }
}