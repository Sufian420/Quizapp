package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: QuizViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextQuestion.setOnClickListener {
            val selectedOptionId =
                binding.RadioButtonGroupId.indexOfChild(findViewById(binding.RadioButtonGroupId.checkedRadioButtonId))

            if (selectedOptionId != -1) {
                viewModel.checkAnswer(selectedOptionId)
                showNextQuestion()
            } else {
                Toast.makeText(this, "Select an option", Toast.LENGTH_SHORT).show()
            }

        }

        showCurrentQuestion()


    }

    private fun showCurrentQuestion() {
        val currentQuestion = viewModel.gettingCurrentQuestion()
        binding.apply {
            QuestionViewText.text = currentQuestion.question
            RadioButton0.text = currentQuestion.option[0]
            RadioButton1.text = currentQuestion.option[1]
            RadioButton2.text = currentQuestion.option[2]
            RadioButton3.text = currentQuestion.option[3]
            RadioButtonGroupId.clearCheck()


        }

    }

    private fun showNextQuestion() {
        val nextQuestion = viewModel.gettingNextQuestion()
        if (nextQuestion != null) {
            showCurrentQuestion()
        } else {
            showResult("Your Score is ${viewModel.getScore()} out of ${viewModel.getQuestionSize()}")

        }
    }

    private fun showResult(msg: String) {
        val alertDialog = AlertDialog.Builder(this).setTitle("Your Score").setMessage(msg)
            .setPositiveButton("Restart") { _, _ ->

                viewModel.restart()
                showCurrentQuestion()
            }.setNegativeButton("Dismiss") { _, _ ->

            binding.nextQuestion.visibility=View.INVISIBLE

        }.setCancelable(false).create()
            alertDialog.show()
    }
}