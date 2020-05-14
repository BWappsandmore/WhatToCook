package at.bwappsandmore.whattocook.viewModel

import at.bwappsandmore.whattocook.base.BaseViewModel
import at.bwappsandmore.whattocook.repository.AppRepository

abstract class MainActivityViewModel : BaseViewModel()

class MainActivityViewModelImpl(private val repository: AppRepository): MainActivityViewModel(){}