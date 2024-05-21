package com.app.recipeandroidapp.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.recipeandroidapp.R
import com.app.recipeandroidapp.model.Meal

class MealAdapter(
    private var meals: List<Meal>,
    private val listener: OnItemClickListener,
    private val isFavoriteList: Boolean = false
) : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(meal: Meal)
        fun onDeleteClick(meal: Meal)
    }

    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val mealName: TextView = itemView.findViewById(R.id.meal_name)
        private val mealCategory: TextView = itemView.findViewById(R.id.meal_category)
        private val mealArea: TextView = itemView.findViewById(R.id.meal_area)
        private val deleteMealButton: Button? = itemView.findViewById(R.id.delete_meal_button)

        init {
            itemView.setOnClickListener(this)
            deleteMealButton?.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDeleteClick(meals[position])
                }
            }
        }

        fun bind(meal: Meal) {
            mealName.text = meal.strMeal
            mealCategory.text = meal.strCategory
            mealArea.text = meal.strArea
            deleteMealButton?.visibility = if (isFavoriteList) View.VISIBLE else View.GONE
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(meals[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val layout = if (isFavoriteList) R.layout.meal_item_favorite else R.layout.meal_item
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(layout, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        holder.bind(meal)
    }

    override fun getItemCount(): Int = meals.size

    fun updateData(newMeals: List<Meal>) {
        meals = newMeals
        notifyDataSetChanged()
    }
}
