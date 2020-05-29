package at.bwappsandmore.whattocook.ui.nosidedish.viewModel

import androidx.lifecycle.LiveData
import at.bwappsandmore.whattocook.base.BaseViewModel
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.room.NoSideDishEntity

abstract class NoSideDishViewModel : BaseViewModel() {
    abstract fun displayAllMeals(): LiveData<List<NoSideDishEntity>>
    abstract fun insertMeal(meal: NoSideDishEntity)
}

class NoSideDishViewModelImpl(private val repository: AppRepository): NoSideDishViewModel(){

    private val getAllMeals: LiveData<List<NoSideDishEntity>> = repository.getAllNoSideDishMeals()
    override fun displayAllMeals() = getAllMeals
    override fun insertMeal(meal: NoSideDishEntity){
        repository.insert(meal)
    }
}