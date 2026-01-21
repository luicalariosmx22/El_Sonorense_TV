package mx.elsonorense.tv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import mx.elsonorense.tv.data.ProgrammingData
import mx.elsonorense.tv.adapter.ProgrammingAdapter

class ProgrammingActivity : AppCompatActivity() {
    
    private lateinit var titleTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProgrammingAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programming)
        
        initViews()
        setupRecyclerView()
        loadProgramming()
    }
    
    private fun initViews() {
        titleTextView = findViewById(R.id.programming_title)
        recyclerView = findViewById(R.id.programming_recycler)
    }
    
    private fun setupRecyclerView() {
        adapter = ProgrammingAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
    
    private fun loadProgramming() {
        val programmingData = ProgrammingData.getTodaysProgramming()
        adapter.submitList(programmingData)
    }
}