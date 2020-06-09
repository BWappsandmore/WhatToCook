package at.bwappsandmore.whattocook.ui.nogarnish.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.room.NoSideDishEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class NoSideDishAdapter(
    private val onActionClicked: (NoSideDishEntity) -> Unit,
    private val onActionLongClicked: (NoSideDishEntity) -> Unit
) : RecyclerView.Adapter<NoSideDishAdapter.NoSideDishViewHolder>() {
    var meals = emptyList<NoSideDishEntity>()
    internal fun setMeals(meals: List<NoSideDishEntity>) {
        this.meals = meals
        notifyDataSetChanged()
    }

    override fun getItemCount() = meals.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoSideDishViewHolder =
        NoSideDishViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        )

    override fun onBindViewHolder(holder: NoSideDishViewHolder, position: Int) {
        holder.bind(meals[position])
    }
    inner class NoSideDishViewHolder(override val containerView: View) :
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

        fun bind(meal: NoSideDishEntity) {
            itemView.meal_name.text = meal.mealName
        }
    }


}