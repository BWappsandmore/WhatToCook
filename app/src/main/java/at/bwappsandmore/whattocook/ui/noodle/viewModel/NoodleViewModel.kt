package at.bwappsandmore.whattocook.ui.noodle.viewModel

import androidx.lifecycle.LiveData
import at.bwappsandmore.whattocook.base.BaseViewModel
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.room.NoodleEntity

abstract class NoodleViewModel : BaseViewModel() {
    abstract fun displayAllMeals(): LiveData<List<NoodleEntity>>
    abstract fun insertMeal(meal: NoodleEntity)
}

class NoodleViewModelImpl(private val repository: AppRepository): NoodleViewModel(){

    private val getAllMeals: LiveData<List<NoodleEntity>> = repository.getAllNoodleMeals()
    override fun displayAllMeals() = getAllMeals
    override fun insertMeal(meal: NoodleEntity){
        repository.insert(meal)
    }
}