package at.bwappsandmore.whattocook.ui.mealplan.viewModel

import androidx.lifecycle.LiveData
import at.bwappsandmore.whattocook.base.BaseViewModel
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.room.MealPlanEntity

abstract class MealPlanViewModel: BaseViewModel() {
    abstract fun displayAllMeals(): LiveData<List<MealPlanEntity>>
    abstract fun insertMeal(meal: MealPlanEntity)
}

class MealPlanViewModelImpl(private val repository: AppRepository): MealPlanViewModel() {

    private val getAllMeals: LiveData<List<MealPlanEntity>> = repository.getAllPlannedMeals()

    override fun displayAllMeals() = getAllMeals

    override fun insertMeal(meal: MealPlanEntity) {
        repository.insert(meal)
    }
}