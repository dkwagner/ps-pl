package com.github.dkwagner.lexer


data class Token(val lexeme: Lexeme,
                 val value: String,
                 val line: Int,
                 val pos: Int)