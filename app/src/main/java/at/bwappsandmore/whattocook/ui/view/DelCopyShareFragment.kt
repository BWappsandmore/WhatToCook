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
import at.bwappsandmore.whattocook.room.MealEntity
import at.bwappsandmore.whattocook.ui.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_del_copy.*
import javax.inject.Inject

class DelCopyShareFragment : BaseFragment<SharedViewModel>() {
    @Inject
    lateinit var repository: AppRepository

    private lateinit var mealEntity: MealEntity
    private var comesFrom = 0
    override fun getLayoutResource(): Int = R.layout.fragment_del_copy
    override fun getViewModelClass(): Class<SharedViewModel> = SharedViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mealEntity = it.getParcelable("item")!!
            comesFrom = it.getInt("fromFragment")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deleteIB.setOnClickListener {
            viewModel.deleteMeal(mealEntity)
            closeFragment()
            (activity as MainActivity).showToast("Meal has been removed")
            viewModel.getAllMeals(comesFrom)
        }

        copyIB.setOnClickListener {
            this.context?.copyToClipboard(mealEntity.mealName)
            (activity as MainActivity).showToast("Copied to clipboard")
            closeFragment()
            viewModel.getAllMeals(comesFrom)
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
            viewModel.getAllMeals(comesFrom)
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