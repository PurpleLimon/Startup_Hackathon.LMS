package com.example.startup_hackathonlms

class Question (
    private val question: String,
    private val answer: String,
    private var weight: Int = 5
): IQuestion {

    override fun incWeight() {
        weight++
    }

    override fun decWeight() {
        weight--
    }

    override fun getWeight(): Int {
        return weight
    }

    override fun getAnswer(): String {
        return answer
    }

    override fun getQuestion(): String {
        return question
    }
}