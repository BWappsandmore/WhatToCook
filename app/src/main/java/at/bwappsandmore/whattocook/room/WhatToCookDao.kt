package at.bwappsandmore.whattocook.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WhatToCookDao {
    @Query("SELECT * FROM NoodleEntity")
    fun getAllNoodleMeals(): LiveData<List<NoodleEntity>>

    @Query("SELECT * FROM PotatoEntity")
    fun getAllPotatoMeals(): LiveData<List<PotatoEntity>>

    @Query("SELECT * FROM RiceEntity")
    fun getAllRiceMeals(): LiveData<List<RiceEntity>>

    @Query("SELECT * FROM StewEntity")
    fun getAllStewMeals(): LiveData<List<StewEntity>>

    @Query("SELECT * FROM NoSideDishEntity")
    fun getAllNoSideDishMeals(): LiveData<List<NoSideDishEntity>>

    @Query("SELECT * FROM FishEntity")
    fun getAllFishMeals(): LiveData<List<FishEntity>>

    @Query("SELECT * FROM MealPlanEntity")
    fun getAllPlannedMeals(): LiveData<List<MealPlanEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noodleMeal: NoodleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(potatoMeal: PotatoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(riceMeal: RiceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stewMeal: StewEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noSideDishMeal: NoSideDishEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fishMeal: FishEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plannedMeal: MealPlanEntity)

    @Delete
    suspend fun delete(noodleMeal: NoodleEntity)

    @Delete
    suspend fun delete(potatoMeal: PotatoEntity)

    @Delete
    suspend fun delete(riceMeal: RiceEntity)

    @Delete
    suspend fun delete(stewMeal: StewEntity)

    @Delete
    suspend fun delete(noSideDishMeal: NoSideDishEntity)

    @Delete
    suspend fun delete(fishMeal: FishEntity)

    @Delete
    suspend fun delete(plannedMeal: MealPlanEntity)




}