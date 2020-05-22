package at.bwappsandmore.whattocook.repository

import androidx.lifecycle.LiveData
import at.bwappsandmore.whattocook.room.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface LocalRepository {

    fun getAllNoodleMeals(): LiveData<List<NoodleEntity>>
    fun getAllPotatoMeals(): LiveData<List<PotatoEntity>>
    fun getAllRiceMeals(): LiveData<List<RiceEntity>>
    fun getAllStewMeals(): LiveData<List<StewEntity>>
    fun getAllNoSideDishMeals(): LiveData<List<NoSideDishEntity>>
    fun getAllFishMeals(): LiveData<List<FishEntity>>
    fun getAllPlannedMeals(): LiveData<List<MealPlanEntity>>

    fun insert(noodleMeal: NoodleEntity)
    fun insert(potatoMeal: PotatoEntity)
    fun insert(riceMeal: RiceEntity)
    fun insert(stewMeal: StewEntity)
    fun insert(noSideDishMeal: NoSideDishEntity)
    fun insert(fishMeal: FishEntity)
    fun insert(plannedMeal: MealPlanEntity)

    fun delete(noodleMeal: NoodleEntity)
    fun delete(potatoMeal: PotatoEntity)
    fun delete(riceMeal: RiceEntity)
    fun delete(stewMeal: StewEntity)
    fun delete(noSideDishMeal: NoSideDishEntity)
    fun delete(fishMeal: FishEntity)
    fun delete(plannedMeal: MealPlanEntity)
}

class AppRepository (private val whatToCookDao: WhatToCookDao) : LocalRepository {

    override fun getAllNoodleMeals() = whatToCookDao.getAllNoodleMeals()
    override fun getAllPotatoMeals() = whatToCookDao.getAllPotatoMeals()
    override fun getAllRiceMeals() = whatToCookDao.getAllRiceMeals()
    override fun getAllStewMeals() = whatToCookDao.getAllStewMeals()
    override fun getAllNoSideDishMeals() = whatToCookDao.getAllNoSideDishMeals()
    override fun getAllFishMeals() = whatToCookDao.getAllFishMeals()
    override fun getAllPlannedMeals() = whatToCookDao.getAllPlannedMeals()

    override fun insert(noodleMeal: NoodleEntity) {
        GlobalScope.launch {
            whatToCookDao.insert(noodleMeal)
        }
    }
    override fun insert(potatoMeal: PotatoEntity){
        GlobalScope.launch {
            whatToCookDao.insert(potatoMeal)
        }
    }
    override fun insert(riceMeal: RiceEntity){
        GlobalScope.launch {
            whatToCookDao.insert(riceMeal)
        }
    }
    override fun insert(stewMeal: StewEntity){
        GlobalScope.launch {
            whatToCookDao.insert(stewMeal)
        }
    }
    override fun insert(noSideDishMeal: NoSideDishEntity){
        GlobalScope.launch {
            whatToCookDao.insert(noSideDishMeal)
        }
    }
    override fun insert(fishMeal: FishEntity){
        GlobalScope.launch {
            whatToCookDao.insert(fishMeal)
        }
    }
    override fun insert(plannedMeal: MealPlanEntity){
        GlobalScope.launch {
            whatToCookDao.insert(plannedMeal)
        }
    }

    override fun delete(noodleMeal: NoodleEntity){
        GlobalScope.launch {
            whatToCookDao.delete(noodleMeal)
        }
    }
    override fun delete(potatoMeal: PotatoEntity){
        GlobalScope.launch {
            whatToCookDao.delete(potatoMeal)
        }
    }
    override fun delete(riceMeal: RiceEntity){
        GlobalScope.launch {
            whatToCookDao.delete(riceMeal)
        }
    }
    override fun delete(stewMeal: StewEntity){
        GlobalScope.launch {
            whatToCookDao.delete(stewMeal)
        }
    }
    override fun delete(noSideDishMeal: NoSideDishEntity){
        GlobalScope.launch {
            whatToCookDao.delete(noSideDishMeal)
        }
    }
    override fun delete(fishMeal: FishEntity){
        GlobalScope.launch {
            whatToCookDao.delete(fishMeal)
        }
    }
    override fun delete(plannedMeal: MealPlanEntity){
        GlobalScope.launch {
            whatToCookDao.delete(plannedMeal)
        }
    }
}