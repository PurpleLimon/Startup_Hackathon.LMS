package com.example.startup_hackathonlms

import java.util.*
import kotlin.random.Random

interface ITag {

    fun getQuestions(): List<Question>
    fun getCurrentQuestion(): Question
    fun getWeight(): Int
    fun getQuestionWeightsSum(): Int

    fun incWeight()

    fun decWeight()

    operator fun get(i: Int): Question

    fun getRandomQuestion(): Question
}

