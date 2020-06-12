package at.bwappsandmore.whattocook.repository

import androidx.lifecycle.LiveData
import at.bwappsandmore.whattocook.room.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface LocalRepository {

    fun getAllMeals(mealType: Int): LiveData<List<MealEntity>>
    fun insert(meal: MealEntity)
    fun delete(meal: MealEntity)

}

class AppRepository (private val whatToCookDao: WhatToCookDao) : LocalRepository {

    override fun getAllMeals(mealType: Int) = whatToCookDao.getAllMeals(mealType)

    override fun insert(meal: MealEntity) {
        GlobalScope.launch {
            whatToCookDao.insert(meal)
        }
    }

    override fun delete(meal: MealEntity){
        GlobalScope.launch {
            whatToCookDao.delete(meal)
        }
    }
}