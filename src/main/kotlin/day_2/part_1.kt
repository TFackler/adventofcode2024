package org.example.day_2

import org.example.Util
import kotlin.math.abs

fun main() {
    println("Advent of Code 2024: Day 2 (1/2)")

    val reports = Util.readFileLineByLine("day_2/part_1/puzzle.txt").map { line ->
        line.split(" ").map { numberString -> Integer.parseInt(numberString) }
    }

    val numberOfSafeReports = reports.count { report -> isReportSafe(report) }
    println("number of safe report: $numberOfSafeReports")
}

fun isReportSafe(report: List<Int>): Boolean {
    if (report.size <= 1) return true
    if (report[0] == report[1]) return false
    val isStrictlyDescending = report[0] > report[1]

    for (i in 0 until report.size - 1) {
        val current = report[i]
        val next = report[i + 1]

        if (isStrictlyDescending && current <= next) {
            return false
        }
        if (!isStrictlyDescending && current >= next) {
            return false
        }
        val levelDifference = abs(current - next)
        if (levelDifference !in 1..3) return false
    }
    return true
}