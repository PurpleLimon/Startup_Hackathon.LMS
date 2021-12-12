package com.example.startup_hackathonlms

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import java.io.File
import java.lang.IllegalArgumentException
import java.nio.file.FileSystem
import java.nio.file.FileSystems
import java.nio.file.Files
import kotlin.random.Random


class Table(fileInputName: String):ITable {
    private val tags = mutableListOf<Tag>()
    private var currentTag: Tag? = null

    init {

        // --------ЧТЕНИЕ ИЗ ФАЙЛА -------- \\
//        val questionsForTag = mutableMapOf<String, MutableList<Question>>()
//        val weights = mutableMapOf<String, Int>()
//        File(fileInputName).bufferedReader().forEachLine{ line ->
//            val tagDetails = line.split(";")
//            val tagName = tagDetails[0]
//            val tagWeight = tagDetails[1].toInt()
//            val question = tagDetails[2]
//            val correctAnswer = tagDetails[3]
//            val questionWeight = tagDetails[4].toInt()
//
//            if (questionsForTag[tagName]?.add(Question(question, correctAnswer, questionWeight)) == null) {
//                weights[tagName] = tagWeight
//                questionsForTag[tagName] = mutableListOf(Question(question, correctAnswer, questionWeight))
//            }
//        }
//
//        for ((key, value) in questionsForTag) {
//            tags.add(Tag(value, null, weights[key]!!, value.sumOf { it.getWeight() }))
//        }

        tags.add (Tag("1_lvl", listOf(
            Question("1 × 1", "1"),
            Question("1 × 2", "2"),
            Question("1 × 3", "3"),
            Question("1 × 4", "4"),
            Question("1 × 5", "5"),
            Question("1 × 6", "6"),
            Question("1 × 7", "7"),
            Question("1 × 8", "8"),
            Question("1 × 9", "9")
        ), null, 5, 45))
        tags.add (Tag("2_lvl", listOf(
            Question("2 × 1", "2"),
            Question("2 × 2", "4"),
            Question("2 × 3", "6"),
            Question("2 × 4", "8"),
            Question("2 × 5", "10"),
            Question("2 × 6", "12"),
            Question("2 × 7", "14"),
            Question("2 × 8", "16"),
            Question("2 × 9", "18")
        ), null, 5, 45))
        tags.add (Tag("3_lvl", listOf(
            Question("3 × 1", "3"),
            Question("3 × 2", "6"),
            Question("3 × 3", "9"),
            Question("3 × 4", "12"),
            Question("3 × 5", "15"),
            Question("3 × 6", "18"),
            Question("3 × 7", "21"),
            Question("3 × 8", "24"),
            Question("3 × 9", "27")
        ), null, 5, 45))
        tags.add (Tag("4_lvl", listOf(
            Question("4 × 1", "4"),
            Question("4 × 2", "8"),
            Question("4 × 3", "12"),
            Question("4 × 4", "16"),
            Question("4 × 5", "20"),
            Question("4 × 6", "24"),
            Question("4 × 7", "28"),
            Question("4 × 8", "32"),
            Question("4 × 9", "36")
        ), null, 5, 45))
        tags.add (Tag("5_lvl", listOf(
            Question("5 × 1", "5"),
            Question("5 × 2", "10"),
            Question("5 × 3", "15"),
            Question("5 × 4", "20"),
            Question("5 × 5", "25"),
            Question("5 × 6", "30"),
            Question("5 × 7", "35"),
            Question("5 × 8", "40"),
            Question("5 × 9", "45")
        ), null, 5, 45))
        tags.add (Tag("6_lvl", listOf(
            Question("6 × 1", "6"),
            Question("6 × 2", "12"),
            Question("6 × 3", "18"),
            Question("6 × 4", "24"),
            Question("6 × 5", "30"),
            Question("6 × 6", "36"),
            Question("6 × 7", "42"),
            Question("6 × 8", "48"),
            Question("6 × 9", "54")
        ), null, 5, 45))
        tags.add (Tag("7_lvl", listOf(
            Question("7 × 1", "7"),
            Question("7 × 2", "14"),
            Question("7 × 3", "21"),
            Question("7 × 4", "28"),
            Question("7 × 5", "35"),
            Question("7 × 6", "42"),
            Question("7 × 7", "49"),
            Question("7 × 8", "56"),
            Question("7 × 9", "63")
        ), null, 5, 45))
        tags.add (Tag("8_lvl", listOf(
            Question("8 × 1", "8"),
            Question("8 × 2", "16"),
            Question("8 × 3", "24"),
            Question("8 × 4", "32"),
            Question("8 × 5", "40"),
            Question("8 × 6", "48"),
            Question("8 × 7", "56"),
            Question("8 × 8", "64"),
            Question("8 × 9", "72")
        ), null, 5, 45))
        tags.add (Tag("9_lvl", listOf(
            Question("9 × 1", "9"),
            Question("9 × 2", "18"),
            Question("9 × 3", "27"),
            Question("9 × 4", "36"),
            Question("9 × 5", "45"),
            Question("9 × 6", "54"),
            Question("9 × 7", "63"),
            Question("9 × 8", "72"),
            Question("9 × 9", "81")
        ), null, 5, 45))
    }

    fun getCurrentTag(): Tag {
        if (currentTag == null)
            throw NullPointerException("Current Tag is not selected")
        return currentTag!!
    }

    fun getCurrentTagOrNull(): Tag? = currentTag

    override fun pickATag(tagName: String) {
        currentTag = tags.find { it.tagName == tagName }

        if (currentTag == null)
            throw IllegalArgumentException("No tag $tagName found")
    }

    override fun getRandomQuestion(): Question = getCurrentTag().getRandomQuestion()

    override fun getOtherChoices(amount: Int): List<String> {
        if (amount > getCurrentTag().getQuestions().size)
            throw IllegalArgumentException("Asked choices amount is greater than available")
        val answers = mutableSetOf<String>()

        while (answers.size < amount)
            answers.add(getCurrentTag().getOtherQuestions().random().getAnswer())
        return answers.toList()
    }
}