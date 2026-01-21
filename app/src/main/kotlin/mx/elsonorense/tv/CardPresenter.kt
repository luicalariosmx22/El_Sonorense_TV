package mx.elsonorense.tv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.leanback.widget.Presenter

class CardPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        // Usar nuestro layout personalizado
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val view = viewHolder.view
        val titleView = view.findViewById<TextView>(R.id.card_title)
        val descriptionView = view.findViewById<TextView>(R.id.card_description)
        
        when (item) {
            is MenuItem -> {
                titleView.text = item.title
                descriptionView.text = item.description
                
                // Añadir un indicador visual según el tipo
                when (item.id) {
                    "livetv" -> titleView.setCompoundDrawablesWithIntrinsicBounds(
                        android.R.drawable.ic_media_play, 0, 0, 0
                    )
                    "programming" -> titleView.setCompoundDrawablesWithIntrinsicBounds(
                        android.R.drawable.ic_menu_agenda, 0, 0, 0
                    )
                }
                titleView.compoundDrawablePadding = 16
            }
            is StreamOption -> {
                titleView.text = item.title
                descriptionView.text = item.description
                titleView.setCompoundDrawablesWithIntrinsicBounds(
                    android.R.drawable.ic_media_play, 0, 0, 0
                )
                titleView.compoundDrawablePadding = 16
            }
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        val view = viewHolder.view
        val titleView = view.findViewById<TextView>(R.id.card_title)
        titleView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
    }
}