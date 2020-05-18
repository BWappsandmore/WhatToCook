package at.bwappsandmore.whattocook.ui.mealplan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.room.MealPlanEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class MealPlanAdapter(private val onActionClicked: (MealPlanEntity) -> Unit) :
    RecyclerView.Adapter<MealPlanAdapter.MealPlanViewHolder>() {
    var meals = emptyList<MealPlanEntity>()

    internal fun setMeals(meals: List<MealPlanEntity>) {
        this.meals = meals
        notifyDataSetChanged()
    }

    override fun getItemCount() = meals.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealPlanViewHolder =
        MealPlanViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        )

    override fun onBindViewHolder(holder: MealPlanViewHolder, position: Int) {
        holder.bind(meals[position])
    }


    inner class MealPlanViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        init {
            containerView.apply {
                setOnClickListener {
                    onActionClicked(meals[adapterPosition])
                }
            }
        }

        fun bind(meal: MealPlanEntity) {
            itemView.meal_name.text = meal.mealName
        }
    }
}

