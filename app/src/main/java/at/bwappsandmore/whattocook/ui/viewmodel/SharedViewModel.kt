package at.bwappsandmore.whattocook.ui.viewmodel

import androidx.lifecycle.LiveData
import at.bwappsandmore.whattocook.base.BaseViewModel
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.room.MealEntity

abstract class SharedViewModel : BaseViewModel() {
    abstract fun getAllMeals(mealType: String): LiveData<List<MealEntity>>
    abstract fun insertMeal(meal: MealEntity)
    abstract fun deleteMeal(meal: MealEntity)
}

class SharedViewModelImpl(private val repository: AppRepository) : SharedViewModel() {

    override fun getAllMeals(mealType: String) = repository.getAllMeals(mealType)


    override fun insertMeal(meal: MealEntity) {
        repository.insert(meal)
    }

    override fun deleteMeal(meal: MealEntity) {
        repository.delete(meal)
    }

}