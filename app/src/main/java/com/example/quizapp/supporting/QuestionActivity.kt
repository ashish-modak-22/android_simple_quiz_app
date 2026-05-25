package com.example.quizapp.supporting

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quizapp.R
import com.example.quizapp.model.Question
import com.example.quizapp.utils.Constants

class QuestionActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var progressBar: ProgressBar
    private lateinit var textviewForProgress: TextView
    private lateinit var textViewForQuestions: TextView
    private lateinit var image: ImageView
    private lateinit var textViewForOption1: TextView
    private lateinit var textViewForOption2: TextView
    private lateinit var textViewForOption3: TextView
    private lateinit var textViewForOption4: TextView
    private lateinit var checkOptionButton: Button

    private var currentPosn = 1
    private lateinit var questionList: MutableList<Question>
    private var selectedAnswer = 0
    private lateinit var currentQuestion: Question
    private var answered = false
    private lateinit var name: String
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        progressBar = findViewById(R.id.progress_bar)
        textviewForProgress = findViewById(R.id.text_view_progress)
        textViewForQuestions = findViewById(R.id.question_text_view)
        image = findViewById(R.id.flag_images)

        textViewForOption1 = findViewById(R.id.textView_first_option)
        textViewForOption2 = findViewById(R.id.textView_second_option)
        textViewForOption3 = findViewById(R.id.textView_third_option)
        textViewForOption4 = findViewById(R.id.textView_fourth_option)

        checkOptionButton = findViewById(R.id.button_checking)

        textViewForOption1.setOnClickListener(this)
        textViewForOption2.setOnClickListener(this)
        textViewForOption3.setOnClickListener(this)
        textViewForOption4.setOnClickListener(this)

        checkOptionButton.setOnClickListener(this)

        questionList = Constants.getQuestions()

        Log.d("QuestionSize", "${questionList.size}")

        showNextQuestion()

        if(intent.hasExtra(Constants.USER_NAME)){
            name = intent.getStringExtra(Constants.USER_NAME)!!
        }
    }

    private fun showNextQuestion() {


        if (currentPosn == questionList.size+1) {
            checkOptionButton.text = getString(R.string.finish)
            Intent(this, ResultActivity::class.java).also{
                it.putExtra(Constants.USER_NAME, name)
                it.putExtra(Constants.SCORE, score)
                it.putExtra(Constants.TOTAL_QUESTIONS, questionList.size)
                startActivity(it)
            }
        } else {
            checkOptionButton.text =
                getString(R.string.check_your_answer)
            resetTheOptions()
            selectedAnswer = 0

            val question = questionList[currentPosn - 1]

            currentQuestion = question

            image.setImageResource(question.image)

            progressBar.progress = currentPosn

            textviewForProgress.text =
                "$currentPosn/${progressBar.max}"

            textViewForQuestions.text = question.question

            textViewForOption1.text = question.optionOne
            textViewForOption2.text = question.optionTwo
            textViewForOption3.text = question.optionThree
            textViewForOption4.text = question.optionFour
        }

        currentPosn++
        answered = false
    }

    private fun resetTheOptions() {

        val options = mutableListOf<TextView>()

        options.add(textViewForOption1)
        options.add(textViewForOption2)
        options.add(textViewForOption3)
        options.add(textViewForOption4)

        for (option in options) {

            option.setTextColor(Color.parseColor("#7A8089"))

            option.typeface = Typeface.DEFAULT

            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.option_bg
            )
        }
    }

    private fun selectedOption(
        textView: TextView,
        selectOptionNumber: Int
    ) {

        resetTheOptions()

        selectedAnswer = selectOptionNumber

        textView.setTextColor(Color.parseColor("#363A43"))

        textView.setTypeface(
            textView.typeface,
            Typeface.BOLD
        )

        textView.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_bg
        )
    }

    override fun onClick(view: View?) {

        when (view?.id) {

            R.id.textView_first_option -> {
                selectedOption(textViewForOption1, 1)
            }

            R.id.textView_second_option -> {
                selectedOption(textViewForOption2, 2)
            }

            R.id.textView_third_option -> {
                selectedOption(textViewForOption3, 3)
            }

            R.id.textView_fourth_option -> {
                selectedOption(textViewForOption4, 4)
            }

            R.id.button_checking -> {

                if (!answered) {
                    checkAnswer()
                } else {
                    showNextQuestion()
                }
            }
        }
    }

    private fun checkAnswer() {

        answered = true

        if (selectedAnswer == currentQuestion.correctOption) {

            score++
            when (selectedAnswer) {

                1 -> {
                    textViewForOption1.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.correct_option_bg
                        )
                }

                2 -> {
                    textViewForOption2.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.correct_option_bg
                        )
                }

                3 -> {
                    textViewForOption3.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.correct_option_bg
                        )
                }

                4 -> {
                    textViewForOption4.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.correct_option_bg
                        )
                }
            }

        } else {

            when (selectedAnswer) {

                1 -> {
                    textViewForOption1.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.wrong_option_bg
                        )
                }

                2 -> {
                    textViewForOption2.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.wrong_option_bg
                        )
                }

                3 -> {
                    textViewForOption3.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.wrong_option_bg
                        )
                }

                4 -> {
                    textViewForOption4.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.wrong_option_bg
                        )
                }
            }

            when (currentQuestion.correctOption) {

                1 -> {
                    textViewForOption1.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.correct_option_bg
                        )
                }

                2 -> {
                    textViewForOption2.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.correct_option_bg
                        )
                }

                3 -> {
                    textViewForOption3.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.correct_option_bg
                        )
                }

                4 -> {
                    textViewForOption4.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.correct_option_bg
                        )
                }
            }
        }

        checkOptionButton.text = "Next"
    }
}