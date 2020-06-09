package at.bwappsandmore.whattocook.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WhatToCookDao {
    @Query("SELECT * FROM MealEntity WHERE mealType = :mealType")
    fun getAllMeals(mealType: String): LiveData<List<MealEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meal: MealEntity)

    @Delete
    suspend fun delete(meal: MealEntity)
}