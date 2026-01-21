package mx.elsonorense.tv.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mx.elsonorense.tv.R
import mx.elsonorense.tv.model.ProgramCategory

class ProgrammingAdapter : ListAdapter<ProgramCategory, ProgrammingAdapter.CategoryViewHolder>(CategoryDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_program_category, parent, false)
        return CategoryViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryTitle: TextView = itemView.findViewById(R.id.category_title)
        private val programsRecyclerView: RecyclerView = itemView.findViewById(R.id.programs_recycler)
        private val programsAdapter = ProgramsAdapter()
        
        init {
            programsRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            programsRecyclerView.adapter = programsAdapter
        }
        
        fun bind(category: ProgramCategory) {
            categoryTitle.text = category.name
            programsAdapter.submitList(category.programs)
        }
    }
    
    private class CategoryDiffCallback : DiffUtil.ItemCallback<ProgramCategory>() {
        override fun areItemsTheSame(oldItem: ProgramCategory, newItem: ProgramCategory): Boolean {
            return oldItem.name == newItem.name
        }
        
        override fun areContentsTheSame(oldItem: ProgramCategory, newItem: ProgramCategory): Boolean {
            return oldItem == newItem
        }
    }
}