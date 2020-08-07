package at.bwappsandmore.whattocook.ui.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import at.bwappsandmore.whattocook.MainActivity
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.base.BaseFragment
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.room.ListMealsEntity
import at.bwappsandmore.whattocook.ui.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_del_copy.*
import kotlinx.android.synthetic.main.fragment_del_copy.copyIB
import kotlinx.android.synthetic.main.fragment_del_copy.deleteIB
import kotlinx.android.synthetic.main.fragment_del_copy.shareIB
import kotlinx.android.synthetic.main.fragment_delcopy_swap.*
import javax.inject.Inject

class DelCopyShareListFragment : BaseFragment<SharedViewModel>() {
    @Inject
    lateinit var repository: AppRepository

    private lateinit var mealEntity: ListMealsEntity
    override fun getLayoutResource(): Int = R.layout.fragment_delcopy_swap
    override fun getViewModelClass(): Class<SharedViewModel> = SharedViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mealEntity = it.getParcelable("item")!!
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swapIB.setOnClickListener {
            viewModel.apply {
                deleteMeal(mealEntity)
                getRandomMeal(mealEntity.mealType)
                getListMeals()
            }
            closeFragment()
        }

        deleteIB.setOnClickListener {
            viewModel.deleteMeal(mealEntity)
            closeFragment()
            (activity as MainActivity).showToast("Meal has been removed")
        }

        copyIB.setOnClickListener {
            this.context?.copyToClipboard(mealEntity.mealName)
            (activity as MainActivity).showToast("Copied to clipboard")
            closeFragment()
        }

        shareIB.setOnClickListener {
            val sendMessage = mealEntity.mealName
            val sendIntent = Intent().apply{
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_SUBJECT, "WhatToCook")
                putExtra(Intent.EXTRA_TEXT, sendMessage)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, "WhatToCook")
            startActivity(shareIntent)
            closeFragment()
        }
    }

    private fun Context.copyToClipboard(text: CharSequence) {
        val clipboard =
            ContextCompat.getSystemService(this.applicationContext, ClipboardManager::class.java)
        val clip = ClipData.newPlainText("label", text)
        clipboard?.setPrimaryClip(clip)
    }

    private fun closeFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .remove(this)
            .commit()
    }
}