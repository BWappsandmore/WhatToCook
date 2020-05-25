package at.bwappsandmore.whattocook.ui.potato.viewModel

import androidx.lifecycle.LiveData
import at.bwappsandmore.whattocook.base.BaseViewModel
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.room.PotatoEntity

abstract class PotatoViewModel: BaseViewModel() {
    abstract fun displayAllMeals(): LiveData<List<PotatoEntity>>
    abstract fun insertMeal(meal: PotatoEntity)
}

class PotatoViewModelImpl (private val repository: AppRepository): PotatoViewModel() {
    private val getAllMeals: LiveData<List<PotatoEntity>> = repository.getAllPotatoMeals()
    override fun displayAllMeals() = getAllMeals
    override fun insertMeal(meal: PotatoEntity) {
        repository.insert(meal)
    }
}