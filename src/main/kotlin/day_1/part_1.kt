package day_1

import org.example.Util
import kotlin.math.abs

fun main() {
    println("Advent of Code 2024: Day 1 (1/2)")

    val lines = Util.readFileLineByLine("day_1/part_1/puzzle.txt")

    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()

    lines.forEach {
        val split = it.split("   ")
        leftList.add(split[0].toInt())
        rightList.add(split[1].toInt())
    }

    leftList.sort()
    rightList.sort()

    var totalDistance = 0
    for (i in 0 until leftList.size) {
        val left = leftList.removeAt(0)
        val right = rightList.removeAt(0)
        totalDistance += abs(left - right)
    }

    println("Total distance: $totalDistance")
}
