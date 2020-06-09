package at.bwappsandmore.whattocook.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class MealEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name="mealType")
    val mealType: String,
    @ColumnInfo(name = "mealName")
    var mealName: String
): Parcelable