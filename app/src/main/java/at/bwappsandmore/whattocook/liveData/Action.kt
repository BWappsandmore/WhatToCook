package at.bwappsandmore.whattocook.liveData

import at.bwappsandmore.whattocook.room.MealEntity

sealed class Action {
    class fromMealList(val meal: MealEntity) : Action()
    object OnError : Action()
}