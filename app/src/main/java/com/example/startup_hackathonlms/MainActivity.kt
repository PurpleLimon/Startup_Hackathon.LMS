package com.example.startup_hackathonlms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    val falseAnswersAmount = 3

    fun displayQuestion() {
        val table:ITable
        table.pickATag()

        val question = table.getRandomQuestion()
        val falseAnswers = table.getOtherChoices(falseAnswersAmount)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayQuestion()
    }
}