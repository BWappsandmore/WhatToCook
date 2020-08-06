package at.bwappsandmore.whattocook.room

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Single

@Dao
interface WhatToCookDao {
    @Query("SELECT * FROM MealEntity WHERE mealType = :mealType")
    fun getAllMeals(mealType: Int): LiveData<List<MealEntity>>

    @Query("SELECT * FROM ListMealsEntity")
    fun getListMeals(): LiveData<List<ListMealsEntity>>

    @Query("SELECT * FROM MealEntity WHERE mealType = :mealType ORDER BY random() LIMIT 1")
    fun getRandomMeal(mealType: Int): Single<MealEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meal: MealEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meal:ListMealsEntity)

    @Delete
    suspend fun delete(meal: MealEntity)

    @Delete
    suspend fun delete(meal: ListMealsEntity)

    @Query ("DELETE FROM ListMealsEntity")
    fun deleteAll()

    @Query("UPDATE MealEntity SET mealName =:mealName, mealType= :mealType WHERE id= :id")
    fun updateMeal(mealName: String, mealType:Int, id:Int): Int
}