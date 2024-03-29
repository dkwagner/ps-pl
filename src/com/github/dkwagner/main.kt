package com.github.dkwagner

import com.github.dkwagner.parser.Parser
import kotlin.system.measureTimeMillis

/**
 * Initial user interaction for lexer
 */
fun main() {

    val executionTime = measureTimeMillis {
        Parser().parseFile("resources/variable_decl.ps")
    }

    println("\nLexing and Parsing complete with no errors! Good job. The process took approximately $executionTime ms to complete!")
}