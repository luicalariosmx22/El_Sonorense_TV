package mx.elsonorense.tv

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import mx.elsonorense.tv.adapter.NetflixAdapter
import mx.elsonorense.tv.data.ProgrammingData

class MainActivity : AppCompatActivity() {

    private lateinit var timeDisplay: TextView
    private lateinit var dateDisplay: TextView
    private lateinit var liveRecycler: RecyclerView
    private lateinit var featuredRecycler: RecyclerView
    private lateinit var continueRecycler: RecyclerView
    private lateinit var sidebar: View
    private lateinit var sidebarOverlay: View
    private lateinit var menuButton: ImageView
    
    private lateinit var liveAdapter: NetflixAdapter
    private lateinit var featuredAdapter: NetflixAdapter
    private lateinit var continueAdapter: NetflixAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupRecyclers()
        loadData()
        startTimeUpdates()
    }

    private fun initViews() {
        timeDisplay = findViewById(R.id.time_display)
        dateDisplay = findViewById(R.id.date_display)
        liveRecycler = findViewById(R.id.live_recycler)
        featuredRecycler = findViewById(R.id.featured_recycler)
        continueRecycler = findViewById(R.id.continue_recycler)
        sidebar = findViewById(R.id.sidebar)
        sidebarOverlay = findViewById(R.id.sidebar_overlay)
        menuButton = findViewById(R.id.menu_button)
        
        // Hero banner click listeners
        findViewById<ImageView>(R.id.hero_play_button).setOnClickListener {
            startProgrammingActivity()
        }
        
        findViewById<TextView>(R.id.hero_more_info).setOnClickListener {
            // TODO: Show program details
        }
        
        // Sidebar controls
        menuButton.setOnClickListener {
            toggleSidebar()
        }
        
        sidebarOverlay.setOnClickListener {
            closeSidebar()
        }
        
        setupSidebarNavigation()
    }

    private fun setupRecyclers() {
        // Horizontal layout for Netflix-style rows
        liveAdapter = NetflixAdapter()
        liveRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        liveRecycler.adapter = liveAdapter
        
        featuredAdapter = NetflixAdapter()
        featuredRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        featuredRecycler.adapter = featuredAdapter
        
        continueAdapter = NetflixAdapter()
        continueRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        continueRecycler.adapter = continueAdapter
    }

    private fun loadData() {
        val programmingData = ProgrammingData.getTodaysProgramming()
        
        // Filter live programs
        val livePrograms = programmingData.flatMap { it.programs }.filter { it.isLive }
        liveAdapter.submitList(livePrograms)
        
        // Featured programs (all programs for now)
        val featuredPrograms = programmingData.flatMap { it.programs }
        featuredAdapter.submitList(featuredPrograms)
        
        // Continue watching (sample data)
        val continuePrograms = featuredPrograms.take(4)
        continueAdapter.submitList(continuePrograms)
    }

    private fun startTimeUpdates() {
        updateTime()
        // Update every minute
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread { updateTime() }
            }
        }, 60000, 60000)
    }

    private fun updateTime() {
        val now = Date()
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val dateFormat = SimpleDateFormat("EEEE, d MMMM", Locale("es", "ES"))
        
        timeDisplay.text = timeFormat.format(now)
        dateDisplay.text = dateFormat.format(now)
    }

    private fun startProgrammingActivity() {
        val intent = Intent(this, ProgrammingActivity::class.java)
        startActivity(intent)
    }

    private fun toggleSidebar() {
        if (sidebar.visibility == View.VISIBLE) {
            closeSidebar()
        } else {
            openSidebar()
        }
    }

    private fun openSidebar() {
        sidebar.visibility = View.VISIBLE
        sidebarOverlay.visibility = View.VISIBLE
        sidebar.animate()
            .translationX(0f)
            .setDuration(250)
            .start()
    }

    private fun closeSidebar() {
        sidebar.animate()
            .translationX(-300f)
            .setDuration(250)
            .withEndAction {
                sidebar.visibility = View.GONE
                sidebarOverlay.visibility = View.GONE
            }
            .start()
    }

    private fun setupSidebarNavigation() {
        // Setup navigation clicks in sidebar
        // TODO: Add click listeners for sidebar menu items
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_DPAD_LEFT -> {
                if (sidebar.visibility == View.GONE) {
                    openSidebar()
                    true
                } else {
                    super.onKeyDown(keyCode, event)
                }
            }
            KeyEvent.KEYCODE_BACK, KeyEvent.KEYCODE_ESCAPE -> {
                if (sidebar.visibility == View.VISIBLE) {
                    closeSidebar()
                    true
                } else {
                    super.onKeyDown(keyCode, event)
                }
            }
            KeyEvent.KEYCODE_MENU -> {
                toggleSidebar()
                true
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }
}
