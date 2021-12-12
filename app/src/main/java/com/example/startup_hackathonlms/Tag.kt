package com.example.startup_hackathonlms

import kotlin.random.Random

class Tag(
    val tagName: String,
    private val questions: List<Question>,
    private var currentQuestion: Question?,
    private var weight: Int,
    private var questionWeightsSum: Int
): ITag {

    fun getOtherQuestions(): List<Question> {
        return getQuestions() - getCurrentQuestion()
    }

    override fun getQuestions(): List<Question> {
        return questions
    }

    override fun getCurrentQuestion(): Question {
        if (currentQuestion == null)
            throw NullPointerException("No CurrentQuestion")

        return currentQuestion!!
    }

    override fun getWeight(): Int {
        return weight
    }

    override fun getQuestionWeightsSum(): Int {
        return questionWeightsSum
    }

    override fun incWeight() {
        weight++
    }

    override fun decWeight() {
        weight--
    }

    override fun get(i: Int): Question {
        return questions[i]
    }

    override fun getRandomQuestion(): Question {
        val num = Random.nextInt(Int.MAX_VALUE) % getQuestionWeightsSum() + 1;
        var currentNum = 0
        for (i in 0..questions.size) {
            currentNum += questions[i].getWeight();
            if (currentNum > num) {
                currentQuestion = questions[i];
                return currentQuestion!!;
            }
        }
        currentQuestion = questions.last();
        return currentQuestion!!;
    }
}