package at.bwappsandmore.whattocook.ui.fish.viewModel

import androidx.lifecycle.LiveData
import at.bwappsandmore.whattocook.base.BaseViewModel
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.room.FishEntity

abstract class FishViewModel : BaseViewModel(){
    abstract fun displayAllMeals(): LiveData<List<FishEntity>>
    abstract fun insertMeal(meal: FishEntity)
}

class FishViewModelImpl(private val repository: AppRepository):FishViewModel() {
    private val getAllMeals: LiveData<List<FishEntity>> = repository.getAllFishMeals()
    override fun displayAllMeals() = getAllMeals
    override fun insertMeal(meal: FishEntity){
        repository.insert(meal)
    }
}