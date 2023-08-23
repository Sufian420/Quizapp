package com.example.quizapp

import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel(){
    private val quizQuestions=DataSource.quizQuestion
    private var currentQuestionIndex=0
    private var score=0

    fun gettingCurrentQuestion():QuizQuestion{
        return quizQuestions[currentQuestionIndex]
    }

    fun gettingNextQuestion():QuizQuestion?{
        currentQuestionIndex++
        return if (currentQuestionIndex<quizQuestions.size){
            quizQuestions[currentQuestionIndex]
        }else{
            null
        }
    }

    fun checkAnswer(selectedAnswer:Int):Boolean{
        val currentQuestion=quizQuestions[currentQuestionIndex]
        return if (selectedAnswer==currentQuestion.correctAnswerIndex){
            score++
            true
        }else{
            false
        }
    }

    fun getScore():Int{
        return score
    }

    fun getQuestionSize():Int{
        return quizQuestions.size
    }

    fun restart(){
        currentQuestionIndex=0
    }
}