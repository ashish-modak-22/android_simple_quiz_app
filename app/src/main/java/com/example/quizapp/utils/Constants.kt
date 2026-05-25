package com.example.quizapp.utils

import com.example.quizapp.R
import com.example.quizapp.model.Question

object Constants{

    const val USER_NAME = "user_name"
    const val TOTAL_QUESTIONS = "total_questions"
    const val SCORE = "correct_answers"
    fun getQuestions(): MutableList<Question>{
        val questions = mutableListOf<Question>()

        val quest1 = Question(
            1, "Which continent does the country belong?",
            R.drawable.brazil, "Asia", "South America", "Africa",
            "Oceania", 2
        )
        questions.add(quest1)

        val quest2 = Question(
            2, "Which continent does the country belong?",
            R.drawable.colombia, "South America", "Europe", "Asia",
            "North America", 1
        )
        questions.add(quest2)

        val quest3 = Question(
            3, "Which continent does the country belong?",
            R.drawable.croatia, "Africa", "Oceania", "Europe",
            "South America", 3
        )
        questions.add(quest3)

        val quest4 = Question(
            4, "Which continent does the country belong?",
            R.drawable.germany, "Europe", "Africa", "Asia",
            "Oceania", 1
        )
        questions.add(quest4)

        val quest5 = Question(
            5, "Which continent does the country belong?",
            R.drawable.india, "Africa", "South America", "Antarctica",
            "Asia", 4
        )
        questions.add(quest5)

        val quest6 = Question(
            6, "Which continent does the country belong?",
            R.drawable.israel, "South America", "Asia", "Africa",
            "Oceania", 2
        )
        questions.add(quest6)

        val quest7 = Question(
            7, "Which continent does the country belong?",
            R.drawable.kenya, "Europe", "South America", "Africa",
            "Oceania", 3
        )
        questions.add(quest7)

        val quest8 = Question(
            8, "Which continent does the country belong?",
            R.drawable.newzealand, "Asia", "Antarctica", "Africa",
            "Oceania", 4
        )
        questions.add(quest8)

        val quest9 = Question(
            9, "Which continent does the country belong?",
            R.drawable.southafrica, "Asia", "North America", "Africa",
            "Oceania", 3
        )
        questions.add(quest9)

        val quest10 = Question(
            10, "Which continent does the country belong?",
            R.drawable.unitedstates, "North America", "South America", "Asia",
            "Oceania", 1
        )
        questions.add(quest10)

        return questions
    }
}