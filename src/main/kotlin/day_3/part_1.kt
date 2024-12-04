package org.example.day_3

import org.example.Util

fun main() {
    println("Advent of Code 2024: Day 3 (1/2)")

    val lines = Util.readFileLineByLine("day_3/part_1/puzzle.txt")

    val corruptedMemory = lines.reduce { acc, line -> acc + line }

    val regex = Regex("""mul\(([1-9][0-9]{0,2}),([1-9][0-9]{0,2})\)""")
    val matches = regex.findAll(corruptedMemory)

    val total = matches.map { match ->
        val firstMultiplicand = Integer.parseInt(match.groupValues[1])
        val secondMultiplicand = Integer.parseInt(match.groupValues[2])

        return@map firstMultiplicand * secondMultiplicand
    }.sum()

    println("sum of multiplications: $total")
}