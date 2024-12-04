package org.example.day_4

import org.example.Util

data class Position(val x: Int, val y: Int) {
    fun movedBy(direction: Direction, amount: Int = 1): Position {
        return Position(x + (direction.xOffset * amount), y + (direction.yOffset * amount))
    }
}

enum class Direction(val xOffset: Int, val yOffset: Int) {
    UP(0, -1),
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0),
    UP_LEFT(-1, -1),
    UP_RIGHT(1, -1),
    DOWN_LEFT(-1, 1),
    DOWN_RIGHT(1, 1),
}

fun findWord(
    matrix: List<List<Char>>,
    word: String,
    startPosition: Position,
    direction: Direction
): Boolean {
    var positionWithOffset = startPosition
    for (char in word) {
        if (char != matrix[positionWithOffset.y][positionWithOffset.x]) {
            return false
        }
        positionWithOffset = positionWithOffset.movedBy(direction)
    }
//    println("found in $startPosition and direction $direction")
    return true
}

fun main() {
    println("Advent of Code 2024: Day 4 (1/2)")

    // prep data
    val lines = Util.readFileLineByLine("day_4/part_1/puzzle.txt")
    val wordSearchMatrix: List<List<Char>> = buildList {
        for (line in lines) {
            addLast(line.toCharArray().toList())
        }
    }

    val searchWord = "XMAS"
    var numberOfWordsFound = 0

    // traverse puzzle
    for (i in wordSearchMatrix.indices) {
        for (j in wordSearchMatrix[0].indices) {
            val currentPosition = Position(j, i)

            // check every direction
            for (direction in Direction.entries) {
                // check if search will be out of bounds
                val positionWithMaximumOffset = currentPosition.movedBy(direction, searchWord.length-1)
                if (positionWithMaximumOffset.y !in wordSearchMatrix.indices ||
                    positionWithMaximumOffset.x !in wordSearchMatrix[0].indices
                ) {
                    continue
                }

                if (findWord(wordSearchMatrix, searchWord, currentPosition, direction)) {
                    numberOfWordsFound++
                }
            }
        }
    }

    println("Number of words found: $numberOfWordsFound")
}