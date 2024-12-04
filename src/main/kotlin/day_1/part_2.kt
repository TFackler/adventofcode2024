package org.example.day_1

import org.example.Util

fun main() {
    println("Advent of Code 2024: Day 1 (2/2)")

    // should be 24316233 for puzzle
    // should be 31 for example
    val lines = Util.readFileLineByLine("day_1/part_2/puzzle.txt")

    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()

    lines.forEach {
        val split = it.split("   ")
        leftList.add(split[0].toInt())
        rightList.add(split[1].toInt())
    }

    leftList.sort()
    rightList.sort()

    var totalSimilarityScore = 0

    /**
     * Count the number of occurrences of the target number in the sorted list starting from the startIndex.
     */
    fun countOccurrences(target: Int, startIndex: Int, sortedList: List<Int>): Int {
        var occurrences = 0
        for (i in startIndex until sortedList.size) {
            if (sortedList[i] == target) {
                occurrences++
            } else if (sortedList[i] > target) {
                break
            }
        }
        return occurrences
    }

    var leftIndex = 0
    var rightIndex = 0

    while(leftIndex < leftList.size) {
        var currentLeft = leftList[leftIndex]
        var nextLeft = leftList.getOrElse(leftIndex + 1) { -1 }
        var numberOfOccurrencesInARow = 1

        // count the number of the same occurrences of a number in the left list
        while (currentLeft == nextLeft) {
            numberOfOccurrencesInARow++
            leftIndex++
            currentLeft = nextLeft
            nextLeft = leftList[leftIndex + 1]
        }

        // efficiently count the number of occurrences of the currentLeft in the right list
        val occurrencesInRight = countOccurrences(target = currentLeft, startIndex = rightIndex, rightList)

        // calculate similarity score in a batched way
        totalSimilarityScore += numberOfOccurrencesInARow * (currentLeft * occurrencesInRight)

        // move pointers
        rightIndex += occurrencesInRight
        leftIndex++
    }

    println("Total similarityScore: $totalSimilarityScore")
}