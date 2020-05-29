package at.bwappsandmore.whattocook.ui.rice.viewModel

import androidx.lifecycle.LiveData
import at.bwappsandmore.whattocook.base.BaseViewModel
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.room.RiceEntity

abstract class RiceViewModel : BaseViewModel() {
    abstract fun displayAllMeals(): LiveData<List<RiceEntity>>
    abstract fun insert(riceMeal: RiceEntity)
    abstract fun delete(riceMeal: RiceEntity)
}

class RiceViewModelImpl(private val repository: AppRepository) : RiceViewModel() {

    private val getAllMeals: LiveData<List<RiceEntity>> = repository.getAllRiceMeals()
    override fun displayAllMeals() = getAllMeals

    override fun insert(riceMeal: RiceEntity) {
        repository.insert(riceMeal)
    }

    override fun delete(riceMeal: RiceEntity) {
        repository.delete(riceMeal)
    }
}