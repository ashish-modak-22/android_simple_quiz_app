package com.example.quizapp

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity() {

    private lateinit var recyclerHistory : RecyclerView
    private lateinit var btnBack: AppCompatImageButton
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        recyclerHistory = findViewById(R.id.recyclerScore)
        btnBack = findViewById(R.id.btnBack)
        dbHelper = DBHelper(this)

        setupRecyclerView()

        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {

        val historyList = dbHelper.getScore()

        recyclerHistory.layoutManager = LinearLayoutManager(this)

        recyclerHistory.adapter = HistoryAdapter(historyList)
    }
}