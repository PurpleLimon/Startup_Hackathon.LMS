package com.example.startup_hackathonlms

interface ITable {

    fun pickATag();

    fun getRandomQuestion(): String;

    fun getOtherChoices(amount: Int): List<String>

}