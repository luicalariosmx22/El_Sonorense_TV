package mx.elsonorense.tv

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Siempre mostrar el menú principal
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_browse_fragment, MainBrowseFragment())
                .commit()
        }
    }
    
    private fun isAndroidTV(): Boolean {
        return packageManager.hasSystemFeature(PackageManager.FEATURE_LEANBACK)
    }
    
    private fun openPlayerDirectly() {
        // Abrir directamente el reproductor en celular
        val intent = Intent(this, PlayerActivity::class.java)
        intent.putExtra(PlayerActivity.STREAM_URL_EXTRA, "https://s5.mexside.net:1936/elsonorense/elsonorense/playlist.m3u8")
        intent.putExtra(PlayerActivity.STREAM_TITLE_EXTRA, "🔴 EN VIVO - El Sonorense")
        startActivity(intent)
        finish() // Cerrar MainActivity
    }
}