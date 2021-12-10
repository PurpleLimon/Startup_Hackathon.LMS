package com.example.startup_hackathonlms

interface IQuestion {

    fun incWeight()

    fun decWeight()

    fun getWeight(): Int

    fun getAnswer(): String

    fun getQuestion(): String

}