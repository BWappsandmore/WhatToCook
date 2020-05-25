package at.bwappsandmore.whattocook.ui.potato.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.room.PotatoEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class PotatoAdapter(
    private val onActionClicked: (PotatoEntity) -> Unit,
    private val onActionLongClicked: (PotatoEntity) -> Unit
) :
    RecyclerView.Adapter<PotatoAdapter.PotatoViewHolder>() {
    var meals = emptyList<PotatoEntity>()
    internal fun setMeals(meals: List<PotatoEntity>) {
        this.meals = meals
        notifyDataSetChanged()
    }

    override fun getItemCount() = meals.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PotatoViewHolder =
        PotatoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        )

    override fun onBindViewHolder(holder: PotatoViewHolder, position: Int) {
        holder.bind(meals[position])
    }

    inner class PotatoViewHolder(override val containerView: View) :
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

        fun bind(meal: PotatoEntity) {
            itemView.meal_name.text = meal.mealName
        }

    }
}