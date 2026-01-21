package mx.elsonorense.tv.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mx.elsonorense.tv.R
import mx.elsonorense.tv.model.Program

class NetflixAdapter : ListAdapter<Program, NetflixAdapter.NetflixViewHolder>(ProgramDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NetflixViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_netflix_card, parent, false)
        return NetflixViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: NetflixViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NetflixViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText: TextView = itemView.findViewById(R.id.card_title)
        private val descriptionText: TextView = itemView.findViewById(R.id.card_description)
        private val emojiText: TextView = itemView.findViewById(R.id.program_emoji)
        private val liveIndicator: TextView = itemView.findViewById(R.id.card_live_indicator)
        private val playOverlay: ImageView = itemView.findViewById(R.id.play_overlay)
        private val progressBar: ProgressBar = itemView.findViewById(R.id.progress_bar)
        private val imageContainer: View = itemView.findViewById(R.id.program_image_container)

        fun bind(program: Program) {
            titleText.text = program.title
            descriptionText.text = program.description

            // Asignar emoji según categoría
            emojiText.text = when (program.category) {
                "Entretenimiento" -> if (program.title.contains("Tingo")) "🍳" else "🎭"
                "Noticias" -> "📺"
                "Deportes" -> "⚾"
                "Especiales" -> "🌵"
                else -> "📻"
            }

            // Color de fondo según categoría como HTML
            val backgroundColor = when (program.category) {
                "Entretenimiento" -> if (program.title.contains("Tingo")) 
                    ContextCompat.getColor(itemView.context, R.color.sonorense_orange)
                    else ContextCompat.getColor(itemView.context, R.color.sonorense_yellow)
                "Noticias" -> ContextCompat.getColor(itemView.context, android.R.color.holo_red_dark)
                "Deportes" -> ContextCompat.getColor(itemView.context, android.R.color.holo_blue_dark)
                "Especiales" -> ContextCompat.getColor(itemView.context, android.R.color.holo_green_dark)
                else -> ContextCompat.getColor(itemView.context, R.color.sonorense_orange_dark)
            }
            imageContainer.setBackgroundColor(backgroundColor)

            // Mostrar indicador EN VIVO
            if (program.isLive) {
                liveIndicator.visibility = View.VISIBLE
                titleText.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.sonorense_yellow)
                )
                playOverlay.visibility = View.VISIBLE
            } else {
                liveIndicator.visibility = View.GONE
                titleText.setTextColor(
                    ContextCompat.getColor(itemView.context, android.R.color.white)
                )
                playOverlay.visibility = View.GONE
            }

            // Progress bar para "Continúa Viendo" (simulado)
            if (adapterPosition < 3) { // Primeros 3 elementos
                progressBar.visibility = View.VISIBLE
                progressBar.progress = when (adapterPosition) {
                    0 -> 45
                    1 -> 78
                    2 -> 23
                    else -> 0
                }
            } else {
                progressBar.visibility = View.GONE
            }

            // Focus effects
            itemView.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    itemView.scaleX = 1.05f
                    itemView.scaleY = 1.05f
                    playOverlay.visibility = View.VISIBLE
                } else {
                    itemView.scaleX = 1.0f
                    itemView.scaleY = 1.0f
                    if (!program.isLive) {
                        playOverlay.visibility = View.GONE
                    }
                }
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
