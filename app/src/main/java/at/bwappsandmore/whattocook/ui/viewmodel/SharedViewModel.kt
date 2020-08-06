package at.bwappsandmore.whattocook.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import at.bwappsandmore.whattocook.base.BaseViewModel
import at.bwappsandmore.whattocook.enums.MealType
import at.bwappsandmore.whattocook.liveData.Action
import at.bwappsandmore.whattocook.repository.AppRepository
import at.bwappsandmore.whattocook.room.ListMealsEntity
import at.bwappsandmore.whattocook.room.MealEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


abstract class SharedViewModel : BaseViewModel() {
    abstract fun getAllMeals(mealType: Int): LiveData<List<MealEntity>>
    abstract fun getListMeals(): LiveData<List<ListMealsEntity>>
    abstract fun insertMeal(meal: MealEntity)
    abstract fun insertMeal(meal: ListMealsEntity)
    abstract fun deleteMeal(meal: MealEntity)
    abstract fun deleteMeal(meal: ListMealsEntity)
    abstract fun updateMeal(mealName: String, mealType: Int, id: Int)
    abstract fun getRandomMeal(mealType: Int)
    abstract fun generateMealPlan()
    abstract fun deleteAll()

}

class SharedViewModelImpl(private val repository: AppRepository) : SharedViewModel() {

    override fun getAllMeals(mealType: Int) = repository.getAllMeals(mealType)
    override fun getListMeals() = repository.getListMeals()

    override fun insertMeal(meal: MealEntity) {
        repository.insert(meal)
    }

    override fun insertMeal(meal: ListMealsEntity) {
        repository.insert(meal)
    }

    override fun deleteMeal(meal: MealEntity) {
        repository.delete(meal)
    }

    override fun deleteMeal(meal: ListMealsEntity) {
        repository.delete(meal)
    }

    override fun updateMeal(mealName: String, mealType: Int, id: Int) {
        repository.updateMeal(mealName, mealType, id)
    }

    override fun getRandomMeal(mealType: Int) {
        disposables += repository.getRandomMeal(mealType)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                insertMeal(ListMealsEntity(0, it.mealType, it.mealName))
            }, {})
    }

    override fun generateMealPlan() {
        deleteAll()
        enumValues<MealType>().forEach {
            getRandomMeal(it.value)
        }
    }

    override fun deleteAll() {
        repository.deleteAll()
    }
}