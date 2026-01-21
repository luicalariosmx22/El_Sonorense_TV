package mx.elsonorense.tv

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.ui.PlayerView
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import android.widget.Toast

@UnstableApi
class PlayerActivity : AppCompatActivity() {

    private var player: ExoPlayer? = null
    private lateinit var playerView: PlayerView
    private lateinit var playerTitle: TextView
    private lateinit var btnBack: ImageButton
    private lateinit var btnMenu: ImageButton
    private lateinit var headerLayout: LinearLayout
    private val hideHeaderHandler = Handler(Looper.getMainLooper())
    private val hideHeaderRunnable = Runnable { hideHeader() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        initViews()
        setupClickListeners()

        val streamUrl = intent.getStringExtra(STREAM_URL_EXTRA) ?: ""
        val streamTitle = intent.getStringExtra(STREAM_TITLE_EXTRA) ?: "El Sonorense TV"
        
        playerTitle.text = streamTitle
        initializePlayer(streamUrl)
    }
    
    private fun initViews() {
        playerView = findViewById(R.id.player_view)
        playerTitle = findViewById(R.id.player_title)
        btnBack = findViewById(R.id.btn_back)
        btnMenu = findViewById(R.id.btn_menu)
        headerLayout = findViewById(R.id.player_header)
        
        // Ocultar header después de 3 segundos
        scheduleHideHeader()
        
        // Mostrar header al tocar la pantalla
        playerView.setOnClickListener {
            toggleHeader()
        }
    }
    
    private fun setupClickListeners() {
        btnBack.setOnClickListener {
            finish() // Regresar a la actividad anterior
        }
        
        btnMenu.setOnClickListener {
            val intent = Intent(this, ProgrammingActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initializePlayer(streamUrl: String) {
        player = ExoPlayer.Builder(this).build().also { exoPlayer ->
            playerView.player = exoPlayer

            // Create HLS MediaSource specifically for live streaming
            val dataSourceFactory = DefaultHttpDataSource.Factory()
            val hlsMediaSource = HlsMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(streamUrl))
            
            // Set up error listener
            exoPlayer.addListener(object : Player.Listener {
                override fun onPlayerError(error: PlaybackException) {
                    super.onPlayerError(error)
                    Toast.makeText(this@PlayerActivity, 
                        "Error de transmisión: El stream puede estar offline", 
                        Toast.LENGTH_LONG).show()
                }
            })
            
            exoPlayer.setMediaSource(hlsMediaSource)
            exoPlayer.playWhenReady = true
            exoPlayer.prepare()
        }
    }

    override fun onStart() {
        super.onStart()
        if (player == null) {
            val streamUrl = intent.getStringExtra(STREAM_URL_EXTRA) ?: ""
            initializePlayer(streamUrl)
        }
    }

    override fun onResume() {
        super.onResume()
        player?.let { exoPlayer ->
            if (exoPlayer.playbackState == ExoPlayer.STATE_IDLE) {
                exoPlayer.prepare()
            }
            exoPlayer.playWhenReady = true
        }
    }

    override fun onPause() {
        super.onPause()
        player?.playWhenReady = false
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    private fun releasePlayer() {
        player?.run {
            release()
        }
        player = null
        hideHeaderHandler.removeCallbacks(hideHeaderRunnable)
    }
    
    private fun scheduleHideHeader() {
        hideHeaderHandler.removeCallbacks(hideHeaderRunnable)
        hideHeaderHandler.postDelayed(hideHeaderRunnable, 3000) // 3 segundos
    }
    
    private fun hideHeader() {
        headerLayout.visibility = View.GONE
    }
    
    private fun showHeader() {
        headerLayout.visibility = View.VISIBLE
        scheduleHideHeader()
    }
    
    private fun toggleHeader() {
        if (headerLayout.visibility == View.VISIBLE) {
            hideHeader()
        } else {
            showHeader()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

    companion object {
        const val STREAM_URL_EXTRA = "stream_url"
        const val STREAM_TITLE_EXTRA = "stream_title"
    }
}