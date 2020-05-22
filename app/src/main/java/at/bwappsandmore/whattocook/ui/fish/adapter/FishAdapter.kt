package at.bwappsandmore.whattocook.ui.fish.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.room.FishEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class FishAdapter(
    private val onActionClicked: (FishEntity) -> Unit,
    private val onActionLongClicked: (FishEntity) -> Unit
) :
    RecyclerView.Adapter<FishAdapter.FishViewHolder>() {

    var meals = emptyList<FishEntity>()

    internal fun setMeals(meals: List<FishEntity>) {
        this.meals = meals
        notifyDataSetChanged()
    }

    override fun getItemCount() = meals.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FishViewHolder =
        FishViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        )

    override fun onBindViewHolder(holder: FishViewHolder, position: Int) {
        holder.bind(meals[position])
    }

    inner class FishViewHolder(override val containerView: View) :
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


        fun bind(meal: FishEntity) {
            itemView.meal_name.text = meal.mealName
        }
    }
}