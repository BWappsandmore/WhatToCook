package at.bwappsandmore.whattocook.ui.rice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.room.RiceEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class RiceAdapter(
    private val onActionClicked: (RiceEntity) -> Unit,
    private val onActionLongClicked: (RiceEntity) -> Unit
) :
    RecyclerView.Adapter<RiceAdapter.RiceViewHolder>() {
    var meals = emptyList<RiceEntity>()
    internal fun setMeals(meals: List<RiceEntity>) {
        this.meals = meals
        notifyDataSetChanged()
    }

    override fun getItemCount() = meals.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiceViewHolder =
        RiceViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        )

    override fun onBindViewHolder(holder: RiceViewHolder, position: Int) {
        holder.bind(meals[position])
    }

    inner class RiceViewHolder(override val containerView: View) :
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

        fun bind(meal: RiceEntity) {
            itemView.meal_name.text = meal.mealName
        }
    }
}
