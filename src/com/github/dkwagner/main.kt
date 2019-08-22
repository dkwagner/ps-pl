package com.github.dkwagner

import kotlin.system.measureTimeMillis

/**
 * Initial user interaction for lexer
 */
fun main() {

    val input = "resources/test.ps"

    val lexer  = Lexer()

    val v1Time = measureTimeMillis {
        lexer.lex(input)
    }

    println("Time in millis to lex = $v1Time")
}