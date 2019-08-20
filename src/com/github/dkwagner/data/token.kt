package com.github.dkwagner.data

data class Token( val lexeme: Lexeme, val value: String) {

    override fun toString(): String {
        return "<" + lexeme.name + ">:<" + value + ">"
    }
}