package org.example

/**
 * Loads a list of manuals and sorting pairs.
 */
fun loadManualsAndSortingPairs(fileName: String): Pair<List<List<Int>>, List<Pair<Int, Int>>> {
    val lines = Util.readFileLineByLine(fileName)
    val sortingPairs = mutableListOf<Pair<Int, Int>>()
    val manualsToPrint = mutableListOf<List<Int>>()

    var parseSortingPairs = true
    for (line in lines) {
        if (line.isBlank()) {
            parseSortingPairs = false
            continue
        }

        if (parseSortingPairs) {
            val (a, b) = line.split("|")
            sortingPairs.add(Pair(a.toInt(), b.toInt()))
        } else {
            manualsToPrint.add(line.split(",").map { it.toInt() })
        }
    }
    return manualsToPrint to sortingPairs
}

/**
 * Checks whether the pages in the given [manualPages] are sorted in ascending order according to [sortingPairs].
 */
fun isManualLegal(manualPages: List<Int>, sortingPairs: List<Pair<Int, Int>>): Boolean {
    val illegalNextPages = mutableSetOf<Int>()
    var isManualLegal = true

    for (page in manualPages) {
        if (page in illegalNextPages) {
            isManualLegal = false
            break
        }

        sortingPairs
            .filter { it.second == page }
            .forEach { pair -> illegalNextPages.add(pair.first) }
    }
    return isManualLegal
}

fun part_1() {
    println("Advent of Code 2024: Day 5 (1/2)")

    val (manualsToPrint, sortingPairs) = loadManualsAndSortingPairs("day_5/puzzle.txt")
    var sumOfMiddlePages = 0

    for (manual in manualsToPrint) {
        if (isManualLegal(manual, sortingPairs)) {
            sumOfMiddlePages += manual[manual.size / 2]
        }
    }

    println("Sum of middle pages of valid manuals: $sumOfMiddlePages")

}

/**
 * Sorts the given [manualPages] according to the order given by [sortingPairs] using bubble sort.
 */
fun sortedManual(manualPages: List<Int>, sortingPairs: List<Pair<Int, Int>>): List<Int> {
    val sortedManualList = manualPages.toMutableList()

    // bubble sort
    repeat(sortedManualList.size) {
        var swappedAnyPage = false
        for (i in 0 until sortedManualList.size - 1) {
            if (sortingPairs.any { it.first == sortedManualList[i+1] && it.second == sortedManualList[i] }) {
                val temp = sortedManualList[i]
                sortedManualList[i] = sortedManualList[i+1]
                sortedManualList[i+1] = temp
                swappedAnyPage = true
            }
        }
        if (!swappedAnyPage) {
            return@repeat
        }
    }
    return sortedManualList
}

fun part_2() {
    println("Advent of Code 2024: Day 5 (2/2)")

    val (manualsToPrint, sortingPairs) = loadManualsAndSortingPairs("day_5/puzzle.txt")

    val invalidManuals = manualsToPrint.filter { manual ->
        !isManualLegal(manual, sortingPairs)
    }

    val sumOfMiddlePages = invalidManuals.sumOf { manual -> sortedManual(manual, sortingPairs)[manual.size / 2] }
    println("Sum of middle pages of sorted invalid manuals manuals: $sumOfMiddlePages")
}

fun main() {
    part_1()
    part_2()
}