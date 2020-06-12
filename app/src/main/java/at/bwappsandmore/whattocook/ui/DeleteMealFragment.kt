package at.bwappsandmore.whattocook.ui

import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.base.BaseFragment
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.ui.viewmodel.SharedViewModel
import javax.inject.Inject

class DeleteMealFragment : BaseFragment<SharedViewModel>() {
    @Inject
    lateinit var repository: AppRepository
    override fun getLayoutResource(): Int = R.layout.fragment_delete
    override fun getViewModelClass(): Class<SharedViewModel> = SharedViewModel::class.java
}