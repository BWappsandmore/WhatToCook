package at.bwappsandmore.whattocook.ui.stew.viewModel

import androidx.lifecycle.LiveData
import at.bwappsandmore.whattocook.base.BaseViewModel
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.room.StewEntity

abstract class StewViewModel : BaseViewModel() {
    abstract fun displayAllMeals(): LiveData<List<StewEntity>>
    abstract fun insert(stewMeal: StewEntity)
    abstract fun delete(stewMeal: StewEntity)
}

class StewViewModelImpl(private val repository: AppRepository) : StewViewModel() {

    private val getAllMeals: LiveData<List<StewEntity>> = repository.getAllStewMeals()
    override fun displayAllMeals() = getAllMeals

    override fun insert(stewMeal: StewEntity) {
        repository.insert(stewMeal)
    }

    override fun delete(stewMeal: StewEntity) {
        repository.delete(stewMeal)
    }
}