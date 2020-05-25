package at.bwappsandmore.whattocook.ui.noodle.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.room.NoodleEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class NoodleAdapter(
    private val onActionClicked: (NoodleEntity) -> Unit,
    private val onActionLongClicked: (NoodleEntity) -> Unit
) :
    RecyclerView.Adapter<NoodleAdapter.NoodleViewHolder>() {
    var meals = emptyList<NoodleEntity>()
    internal fun setMeals(meals: List<NoodleEntity>) {
        this.meals = meals
        notifyDataSetChanged()
    }

    override fun getItemCount() = meals.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoodleViewHolder =
        NoodleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        )

    override fun onBindViewHolder(holder: NoodleViewHolder, position: Int) {
        holder.bind(meals[position])
    }

    inner class NoodleViewHolder(override val containerView: View) :
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

        fun bind(meal: NoodleEntity) {
            itemView.meal_name.text = meal.mealName
        }
    }
}