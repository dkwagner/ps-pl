package com.github.dkwagner

import com.github.dkwagner.lexer.Lexer
import java.io.File
import kotlin.system.measureTimeMillis

/**
 * Initial user interaction for lexer
 */
fun main() {

    val input = File("resources/full_test.ps").readText()

    val executionTime = measureTimeMillis {
        Lexer(input).printTokens()
    }

    println("Execution time: $executionTime")
}