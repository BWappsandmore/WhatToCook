package at.bwappsandmore.whattocook.ui.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.base.BaseFragment
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.room.MealEntity
import at.bwappsandmore.whattocook.ui.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_del_copy.*
import javax.inject.Inject

class DelCopyMealFragment : BaseFragment<SharedViewModel>() {
    @Inject
    lateinit var repository: AppRepository

    private lateinit var mealEntity: MealEntity
    override fun getLayoutResource(): Int = R.layout.fragment_del_copy
    override fun getViewModelClass(): Class<SharedViewModel> = SharedViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mealEntity = it.getParcelable("item")!!
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deleteIB.setOnClickListener {
            viewModel.deleteMeal(mealEntity)
            requireActivity().supportFragmentManager.beginTransaction()
                .remove(this)
                .commit()
        }

        copyIB.setOnClickListener {
            this.context?.copyToClipboard(mealEntity.mealName)
        }
    }

    private fun Context.copyToClipboard(text: CharSequence) {
        val clipboard =
            ContextCompat.getSystemService(this.applicationContext, ClipboardManager::class.java)
        val clip = ClipData.newPlainText("label", text)
        clipboard?.setPrimaryClip(clip)
    }
}