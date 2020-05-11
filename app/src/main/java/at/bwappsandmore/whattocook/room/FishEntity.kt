package at.bwappsandmore.whattocook.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class FishEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "mealName")
    var mealName: String,
    @ColumnInfo(name = "sideDish")
    var sideDish: String
): Parcelable