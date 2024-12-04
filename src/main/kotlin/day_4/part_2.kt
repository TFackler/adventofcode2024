package org.example.day_4

import org.example.Util

fun main() {
    println("Advent of Code 2024: Day 4 (2/2)")

    // prep data
    val lines = Util.readFileLineByLine("day_4/part_2/puzzle.txt")
    val wordSearchMatrix: List<List<Char>> = mutableListOf()
    for (line in lines) {
        wordSearchMatrix.addLast(line.toCharArray().toList())
    }

    val searchWord = "MAS"
    require(searchWord.length % 2 == 1) { "center undefined for words with even number of letters" }

    var numberOfXFormationsFound = 0
    val centerOffset = searchWord.length / 2

    // traverse puzzle
    for (i in wordSearchMatrix.indices) {
        for (j in wordSearchMatrix[0].indices) {
            val currentPosition = Position(j, i)

            // check bounds
            val upperLeftCorner = currentPosition.movedBy(Direction.UP_LEFT, centerOffset)
            val lowerRightCorner = currentPosition.movedBy(Direction.DOWN_RIGHT, centerOffset)
            val lowerLeftCorner = currentPosition.movedBy(Direction.DOWN_LEFT, centerOffset)
            val upperRightCorner = currentPosition.movedBy(Direction.UP_RIGHT, centerOffset)
            if (upperLeftCorner.x < 0 || upperLeftCorner.y < 0 ||
                lowerRightCorner.x >= wordSearchMatrix[0].size || lowerRightCorner.y >= wordSearchMatrix.size
            ) {
                continue
            }

            val diagonalOneValid = findWord(wordSearchMatrix, searchWord, upperLeftCorner, Direction.DOWN_RIGHT) ||
                findWord(wordSearchMatrix, searchWord, lowerRightCorner, Direction.UP_LEFT)
            val diagonalTwoValid = findWord(wordSearchMatrix, searchWord, lowerLeftCorner, Direction.UP_RIGHT) ||
                findWord(wordSearchMatrix, searchWord, upperRightCorner, Direction.DOWN_LEFT)

            if (diagonalOneValid && diagonalTwoValid) {
                numberOfXFormationsFound++
            }
        }
    }

    println("Number of words found: $numberOfXFormationsFound")
}