package mx.elsonorense.tv.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mx.elsonorense.tv.R
import mx.elsonorense.tv.model.Program

class ProgramsAdapter : ListAdapter<Program, ProgramsAdapter.ProgramViewHolder>(ProgramDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_program, parent, false)
        return ProgramViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class ProgramViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val timeText: TextView = itemView.findViewById(R.id.program_time)
        private val titleText: TextView = itemView.findViewById(R.id.program_title)
        private val descriptionText: TextView = itemView.findViewById(R.id.program_description)
        private val liveIndicator: TextView = itemView.findViewById(R.id.live_indicator)
        
        fun bind(program: Program) {
            timeText.text = "${program.startTime} - ${program.endTime}"
            titleText.text = program.title
            descriptionText.text = program.description
            
            if (program.isLive) {
                liveIndicator.visibility = View.VISIBLE
                liveIndicator.text = "EN VIVO"
                liveIndicator.setBackgroundColor(
                    ContextCompat.getColor(itemView.context, R.color.sonorense_orange)
                )
                titleText.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.sonorense_yellow)
                )
            } else {
                liveIndicator.visibility = View.GONE
                titleText.setTextColor(
                    ContextCompat.getColor(itemView.context, android.R.color.white)
                )
            }
        }
    }
    
    private class ProgramDiffCallback : DiffUtil.ItemCallback<Program>() {
        override fun areItemsTheSame(oldItem: Program, newItem: Program): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Program, newItem: Program): Boolean {
            return oldItem == newItem
        }
    }
}