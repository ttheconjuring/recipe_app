package com.example.recipe_app.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipe_app.R
import com.example.recipe_app.data.RecipeResponse

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchAdapterViewHolder>() {
    private val items = ArrayList<RecipeResponse>()
    var itemClickListener: ItemClickListener<RecipeResponse>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.SearchAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview_search, parent, false)
        return SearchAdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder:  SearchAdapter.SearchAdapterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<RecipeResponse>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
    inner class SearchAdapterViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val recipeImage = view.findViewById<ImageView>(R.id.recipe_image)
        private val recipeTitle = view.findViewById<TextView>(R.id.recipe_title)
        fun bind(recipe: RecipeResponse){
            Glide.with(recipeImage)
                .load(recipe.imageUrl)
                .into(recipeImage)
            recipeTitle.text = recipe.title
            recipeImage.setOnClickListener {
                itemClickListener?.onItemClicked(recipe, absoluteAdapterPosition)
            }

        }
    }
}