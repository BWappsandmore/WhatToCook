package at.bwappsandmore.whattocook.ui.stew.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.room.StewEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class StewAdapter(
    private val onActionClicked: (StewEntity) -> Unit,
    private val onActionLongClicked: (StewEntity) -> Unit
) :
    RecyclerView.Adapter<StewAdapter.StewpotViewHolder>() {
    var meals = emptyList<StewEntity>()
    internal fun setMeals(meals: List<StewEntity>) {
        this.meals = meals
        notifyDataSetChanged()
    }

    override fun getItemCount() = meals.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StewpotViewHolder =
        StewpotViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        )

    override fun onBindViewHolder(holder: StewpotViewHolder, position: Int) {
        holder.bind(meals[position])
    }

    inner class StewpotViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        init {
            containerView.apply {
                setOnClickListener {
                    onActionClicked(meals[adapterPosition])
                }
                setOnLongClickListener {
                    onActionLongClicked(meals[adapterPosition])
                    return@setOnLongClickListener true
                }
            }
        }

        fun bind(meal: StewEntity) {
            itemView.meal_name.text = meal.mealName
        }
    }
}
