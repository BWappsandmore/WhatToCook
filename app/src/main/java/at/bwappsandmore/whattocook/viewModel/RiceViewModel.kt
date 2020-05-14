package at.bwappsandmore.whattocook.viewModel

import androidx.lifecycle.LiveData
import at.bwappsandmore.whattocook.base.BaseViewModel
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.room.RiceEntity

abstract class RiceViewModel : BaseViewModel() {
    abstract fun getRiceMeals(): LiveData<List<RiceEntity>>
    abstract fun insert(riceMeal: RiceEntity)
    abstract fun delete(riceMeal: RiceEntity)
}

class RiceViewModelImpl(private val repository: AppRepository) : RiceViewModel() {

    override fun getRiceMeals(): LiveData<List<RiceEntity>>  = repository.getAllRiceMeals()

    override fun insert(riceMeal: RiceEntity) {
        repository.insert(riceMeal)
    }

    override fun delete(riceMeal: RiceEntity) {
        repository.delete(riceMeal)
    }
}