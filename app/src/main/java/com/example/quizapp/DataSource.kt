package com.example.quizapp

object DataSource {

    val quizQuestion= listOf<QuizQuestion>(QuizQuestion("What is the square root of 144?",
        listOf("11","12","13","14"),1),
        QuizQuestion("Which of the following is a prime number?", listOf("4","9","11","16"),2),
        QuizQuestion("What is the value of π (pi) approximately?", listOf("2.71","3.14","4.20","3.00"),1)
        )
}