package com.example.quizapp.supporting

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import com.example.quizapp.DBHelper
import com.example.quizapp.HistoryActivity
import com.example.quizapp.MainActivity
import com.example.quizapp.R
import com.example.quizapp.utils.Constants

class ResultActivity : AppCompatActivity() {

    private lateinit var textViewScore: TextView
    private lateinit var textViewUserName: TextView
    private lateinit var finishButton: Button
    private lateinit var historyButton: AppCompatImageButton
    private lateinit var saveButton: Button
    private lateinit var dbHelper: DBHelper
    private var isSaved = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        textViewScore = findViewById(R.id.textView_Score)
        textViewUserName = findViewById(R.id.textView_userName)
        finishButton = findViewById(R.id.fnsh_btn)
        historyButton = findViewById(R.id.history_button)
        saveButton = findViewById(R.id.save_btn)
        dbHelper = DBHelper(this)

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val score = intent.getIntExtra(Constants.SCORE,0)
        val name = intent.getStringExtra(Constants.USER_NAME)

        textViewScore.text = "You have scored $score out of $totalQuestions"
        textViewUserName.text = name

        finishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also{
                startActivity(it)
            }
        }

        historyButton.setOnClickListener {
            Intent(this, HistoryActivity::class.java).also{
                startActivity(it)
            }
        }

        saveButton.setOnClickListener {

            if(!isSaved) {

                val isInserted = dbHelper.insertScore(
                    score = textViewScore.text.toString()
                )

                if(isInserted){
                    Toast.makeText(this, "Score Saved Successfully", Toast.LENGTH_SHORT).show()
                    isSaved = true
                }
                else{
                    Toast.makeText(this, "Failed to save the score", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Score saved already", Toast.LENGTH_SHORT).show()
            }
        }
    }
}