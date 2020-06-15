package at.bwappsandmore.whattocook.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.enums.ActionType
import at.bwappsandmore.whattocook.room.MealEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class FragmentAdapter(
    private val onActionClicked: (MealEntity, ActionType) -> Unit,
    private val onActionLongClicked: (MealEntity, ActionType) -> Unit
) : RecyclerView.Adapter<FragmentAdapter.FragmentViewHolder>() {

    var meals = emptyList<MealEntity>()
    internal fun setMeals(meals: List<MealEntity>) {
        this.meals = meals
        notifyDataSetChanged()
    }

    override fun getItemCount() = meals.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FragmentAdapter.FragmentViewHolder =
        FragmentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        )

    override fun onBindViewHolder(holder: FragmentViewHolder, position: Int) {
        holder.bind(meals[position])
    }

    inner class FragmentViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        init {
            containerView.apply {
                setOnClickListener {
                    onActionClicked(meals[adapterPosition], ActionType.EDIT)
                }
                setOnLongClickListener {
                    onActionLongClicked(meals[adapterPosition], ActionType.DELCOPY)
                    select(it)
                    return@setOnLongClickListener true
                }
            }
        }

        fun bind(meal: MealEntity) {
            itemView.meal_name.text = meal.mealName
        }

        private fun select(v: View) {
            v.apply {
                setBackgroundColor(Color.parseColor("#5856d8"))
                meal_name.setTextColor(Color.WHITE)
            }
        }

        private fun deselect(v: View) {
            v.apply {
                setBackgroundColor(0x00000000)
                meal_name.setTextColor(Color.BLACK)
            }
        }
    }
}