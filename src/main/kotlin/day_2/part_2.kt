package org.example.day_2

import org.example.Util

fun main() {
    println("Advent of Code 2024: Day 2 (2/2)")

    val reports = Util.readFileLineByLine("day_2/part_2/puzzle.txt").map { line ->
        line.split(" ").map { numberString -> Integer.parseInt(numberString) }
    }

    val numberOfSafeReports = reports.count { report -> isReportSafeWhenDampened(report) }
    println("number of safe report: $numberOfSafeReports")
}

fun isReportSafeWhenDampened(report: List<Int>): Boolean {
    if (report.size <= 1) return true
    if (report[0] == report[1]) {
        return isReportSafe(dampenReport(report, 0)) || isReportSafe(dampenReport(report, 1))
    }
    val isStrictlyDescending = report[0] > report[1]

    val errorIndices: List<Int> = mutableListOf()
    for (i in 0 until report.size - 1) {
        val current = report[i]
        val next = report[i + 1]

        val difference = if (isStrictlyDescending) {
            current - next
        } else {
            next - current
        }
        if (difference !in 1..3) {
            errorIndices.addLast(i)
            errorIndices.addLast(i + 1)
        }
    }

    if (errorIndices.isNotEmpty()) {
        val uniqueErrorIndices = errorIndices.distinct()
        if (1 in uniqueErrorIndices) {
            // if second item causes issues, it might be the first item's fault
            uniqueErrorIndices.addFirst(0)
        }
        for (errorIndex in uniqueErrorIndices) {
            if (isReportSafe(dampenReport(report, errorIndex))) return true
        }
        return false
    }

    return true
}

fun isReportSafeWhenDampenedBruteForce(report: List<Int>): Boolean {
    if (isReportSafe(report)) return true

    for (i in report.indices) {
        if (isReportSafe(dampenReport(report, i))) return true
    }

    return false
}

fun dampenReport(report: List<Int>, faultyIndex: Int): List<Int> {
    require(faultyIndex in report.indices)
    return report.filterIndexed { index, _ -> (index != faultyIndex) }
}