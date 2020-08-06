package at.bwappsandmore.whattocook.repository

import androidx.lifecycle.LiveData
import at.bwappsandmore.whattocook.room.*
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface LocalRepository {

    fun getAllMeals(mealType: Int): LiveData<List<MealEntity>>
    fun getListMeals(): LiveData<List<ListMealsEntity>>
    fun insert(meal: MealEntity)
    fun insert(meal: ListMealsEntity)
    fun delete(meal: MealEntity)
    fun delete(meal: ListMealsEntity)
    fun updateMeal(mealName: String, mealType:Int, id:Int)
    fun getRandomMeal(mealType: Int): Single<MealEntity>
    fun deleteAll()

}

class AppRepository (private val whatToCookDao: WhatToCookDao) : LocalRepository {

    override fun getAllMeals(mealType: Int) = whatToCookDao.getAllMeals(mealType)
    override fun getListMeals() = whatToCookDao.getListMeals()

    override fun getRandomMeal(mealType: Int) = whatToCookDao.getRandomMeal(mealType)
    override fun deleteAll() {
        GlobalScope.launch {
            whatToCookDao.deleteAll()
        }
    }

    override fun insert(meal: MealEntity) {
        GlobalScope.launch {
            whatToCookDao.insert(meal)
        }
    }

    override fun insert(meal: ListMealsEntity) {
        GlobalScope.launch {
            whatToCookDao.insert(meal)
        }
    }

    override fun delete(meal: MealEntity){
        GlobalScope.launch {
            whatToCookDao.delete(meal)
        }
    }

    override fun delete(meal: ListMealsEntity) {
        GlobalScope.launch {
            whatToCookDao.delete(meal)
        }
    }

    override fun updateMeal(mealName: String, mealType: Int, id: Int) {
        GlobalScope.launch {
            whatToCookDao.updateMeal(mealName,mealType,id)
        }
    }
}