package org.example.day_3

import org.example.Util

fun main() {
    println("Advent of Code 2024: Day 3 (2/2)")

    val lines = Util.readFileLineByLine("day_3/part_2/puzzle.txt")

    val corruptedMemory = lines.reduce { acc, line -> acc + line }

    val regex = Regex("""(mul\(([1-9][0-9]{0,2}),([1-9][0-9]{0,2})\))|(do\(\))|(don't\(\))""")
    val matches = regex.findAll(corruptedMemory)

    var doMultiplications = true
    var sum = 0
    for (match in matches) {
        if (match.groupValues[0].contains("do()")) {
            doMultiplications = true
        } else if (match.groupValues[0].contains("don't()")) {
            doMultiplications = false
        } else {
            if (!doMultiplications) continue

            val firstMultiplicand = Integer.parseInt(match.groupValues[2])
            val secondMultiplicand = Integer.parseInt(match.groupValues[3])
            sum += firstMultiplicand * secondMultiplicand
        }
    }

    println("sum of multiplications: $sum")
}