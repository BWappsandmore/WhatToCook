package at.bwappsandmore.whattocook.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import at.bwappsandmore.whattocook.R
import at.bwappsandmore.whattocook.enums.ActionType
import at.bwappsandmore.whattocook.room.ListMealsEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class ListMealsAdapter(
    private val onActionLongClicked: (ListMealsEntity, ActionType) -> Unit
) : RecyclerView.Adapter<ListMealsAdapter.FragmentViewHolder>() {

    var meals : SortedList<ListMealsEntity>

    init {
        meals = SortedList(ListMealsEntity::class.java, object : SortedListAdapterCallback<ListMealsEntity>(this) {
            override fun onInserted(position: Int, count: Int) {
                super.onInserted(position, count)
                notifyItemRangeInserted(position, count)
            }

            override fun onRemoved(position: Int, count: Int) {
                super.onRemoved(position, count)
                notifyItemRangeRemoved(position, count);
            }

            override fun onMoved(fromPosition: Int, toPosition: Int) {
                super.onMoved(fromPosition, toPosition)
                notifyItemMoved(fromPosition, toPosition);
            }

            override fun onChanged(position: Int, count: Int) {
                super.onChanged(position, count)
                notifyItemRangeChanged(position, count)
            }

            override fun areItemsTheSame(
                item1: ListMealsEntity?,
                item2: ListMealsEntity?
            ): Boolean = item1 == item2

            override fun compare(o1: ListMealsEntity?, o2: ListMealsEntity?): Int = 0

            override fun areContentsTheSame(
                oldItem: ListMealsEntity?,
                newItem: ListMealsEntity?
            ): Boolean = oldItem?.id == newItem?.id
        })
    }

    internal fun replaceAll(meals: List<ListMealsEntity>) {
        this.meals.apply {
            beginBatchedUpdates()
            clear()
            addAll(meals)
            endBatchedUpdates()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListMealsAdapter.FragmentViewHolder =
        FragmentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        )

    override fun onBindViewHolder(holder: FragmentViewHolder, position: Int) {
        holder.bind(meals[position])
    }

    override fun getItemCount() = meals.size()

    inner class FragmentViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        init {
            containerView.apply {
                setOnLongClickListener {
                    onActionLongClicked(meals[adapterPosition], ActionType.DELCOPY)
                    select(it)
                    return@setOnLongClickListener true
                }
            }
        }

        fun bind(meal: ListMealsEntity) {
            deselect(itemView)
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