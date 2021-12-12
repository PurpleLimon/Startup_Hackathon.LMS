package com.example.startup_hackathonlms

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val table = Table("input.txt")

    private lateinit var menuLayout: FrameLayout
    private lateinit var playground: LinearLayout

    private val answerButtons = mutableListOf<Button>()
    private lateinit var questionTextView: TextView

    private var heroHP = 3
    private var enemyHP = 3
    private lateinit var heroHearts: List<ImageView>
    private lateinit var enemyHearts: List<ImageView>

    private lateinit var hero: LinearLayout
    private lateinit var enemy: LinearLayout

    private lateinit var playBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        answerButtons.add(findViewById<Button>(R.id.AnswerButton1))
        answerButtons.add(findViewById<Button>(R.id.AnswerButton2))
        answerButtons.add(findViewById<Button>(R.id.AnswerButton3))
        answerButtons.add(findViewById<Button>(R.id.AnswerButton4))

        menuLayout = findViewById<FrameLayout>(R.id.Menu)
        playground = findViewById<LinearLayout>(R.id.Playground)

        questionTextView = findViewById<TextView>(R.id.QuestionBox)
        playBtn = findViewById<Button>(R.id.playBtn)
        playBtn.setOnClickListener {
            menuLayout.visibility = View.GONE
            playground.visibility = View.VISIBLE

            heroHearts.forEach{it.visibility = View.VISIBLE}
            enemyHearts.forEach{it.visibility = View.VISIBLE}

        }

        hero = findViewById(R.id.Hero)
        enemy = findViewById(R.id.Enemy)

        heroHearts = listOf(
            findViewById<ImageView>(R.id.hero_HP_1),
            findViewById<ImageView>(R.id.hero_HP_2),
            findViewById<ImageView>(R.id.hero_HP_3)
        )

        enemyHearts = listOf(
            findViewById<ImageView>(R.id.enemy_HP_1),
            findViewById<ImageView>(R.id.enemy_HP_2),
            findViewById<ImageView>(R.id.enemy_HP_3)
        )


        heroHearts.forEach{it.visibility = View.VISIBLE}
        enemyHearts.forEach{it.visibility = View.VISIBLE}

        table.pickATag("1_lvl")
        displayQuestion()
    }

    private var clickedButton = -1

    private fun buttonFalse() {
        heroHearts.findLast { it.visibility == View.VISIBLE }?.visibility = View.INVISIBLE

        heroHP--
        if (heroHP == 0){
            playground.visibility = View.GONE
            menuLayout.visibility = View.VISIBLE
        }
        displayQuestion()
    }
    private fun buttonRight() {

        enemyHearts.findLast { it.visibility == View.VISIBLE }?.visibility = View.INVISIBLE

        enemyHP--
        if (enemyHP == 0) {
            playground.visibility = View.GONE
            menuLayout.visibility = View.VISIBLE
        }

        displayQuestion()
    }

    private val falseAnswersAmount = 4
    private fun displayQuestion() {

        val question = table.getRandomQuestion()
        questionTextView.text = question.getQuestion()
        val falseAnswers = table.getOtherChoices(falseAnswersAmount)
        val rightButton = Random.nextInt(1, 5)

        answerButtons.forEach {
            it.setOnClickListener { view ->
            clickedButton = answerButtons.indexOfFirst { button -> button.id == view.id }
            buttonFalse()
            }
        }
        answerButtons.forEachIndexed {index, btn ->
            btn.text = falseAnswers[index]
        }

        answerButtons[rightButton - 1].setOnClickListener { view ->
            clickedButton = answerButtons.indexOfFirst { it.id == view.id }
            buttonRight()
        }
        answerButtons[rightButton - 1].text = question.getAnswer()
    }
}