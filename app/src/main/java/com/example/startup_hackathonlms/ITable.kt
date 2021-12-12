package com.example.startup_hackathonlms

interface ITable {

    fun pickATag(tagName: String);

    fun getRandomQuestion(): Question;

    fun getOtherChoices(amount: Int): List<String>

}