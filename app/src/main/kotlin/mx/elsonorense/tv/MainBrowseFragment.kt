package mx.elsonorense.tv

import android.content.Intent
import android.os.Bundle
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.core.content.ContextCompat

class MainBrowseFragment : BrowseSupportFragment() {
    
    private lateinit var rowsAdapter: ArrayObjectAdapter
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        
        title = "El Sonorense TV"
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        
        badgeDrawable = ContextCompat.getDrawable(requireContext(), R.mipmap.ic_launcher)
        
        setupMainMenu()
        onItemViewClickedListener = ItemViewClickedListener()
    }
    
    private fun setupMainMenu() {
        rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        
        // Cada opción en su propia fila (columna)
        
        // Fila 1: Ver TV en Vivo
        val livetvAdapter = ArrayObjectAdapter(CardPresenter())
        val livetvItem = MenuItem(
            id = "livetv",
            title = "Ver TV en Vivo", 
            description = "Disfruta de nuestra transmisión en directo"
        )
        livetvAdapter.add(livetvItem)
        val livetvRow = ListRow(
            HeaderItem(0, ""),
            livetvAdapter
        )
        rowsAdapter.add(livetvRow)
        
        // Fila 2: Ver Programación
        val programmingAdapter = ArrayObjectAdapter(CardPresenter())
        val programmingItem = MenuItem(
            id = "programming",
            title = "Ver Programación",
            description = "Consulta nuestra programación completa"
        )
        programmingAdapter.add(programmingItem)
        val programmingRow = ListRow(
            HeaderItem(1, ""),
            programmingAdapter
        )
        rowsAdapter.add(programmingRow)
        
        // Fila 3: Stream Alternativo
        val streamAdapter = ArrayObjectAdapter(CardPresenter())
        val streamItem = StreamOption(
            title = "Stream Alternativo",
            description = "Señal alternativa HD",
            url = "https://s5.mexside.net:1936/elsonorense/elsonorense/playlist.m3u8"
        )
        streamAdapter.add(streamItem)
        val streamRow = ListRow(
            HeaderItem(2, ""),
            streamAdapter
        )
        rowsAdapter.add(streamRow)
        
        adapter = rowsAdapter
    }
    
    private inner class ItemViewClickedListener : OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            when (item) {
                is MenuItem -> {
                    when (item.id) {
                        "livetv" -> {
                            val intent = Intent(activity, PlayerActivity::class.java).apply {
                                putExtra("stream_url", "https://s5.mexside.net:1936/elsonorense/elsonorense/playlist.m3u8")
                                putExtra("stream_title", "El Sonorense TV - En Vivo")
                            }
                            startActivity(intent)
                        }
                        "programming" -> {
                            val intent = Intent(activity, ProgrammingActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
                is StreamOption -> {
                    if (item.url.isNotEmpty()) {
                        val intent = Intent(activity, PlayerActivity::class.java).apply {
                            putExtra("stream_url", item.url)
                            putExtra("stream_title", item.title)
                        }
                        startActivity(intent)
                    }
                }
            }
        }
    }
}

data class MenuItem(
    val id: String,
    val title: String,
    val description: String
)