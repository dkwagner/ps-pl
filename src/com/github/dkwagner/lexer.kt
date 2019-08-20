package com.github.dkwagner

import com.github.dkwagner.data.Token
import com.github.dkwagner.io.Input

class Lexer {

    fun lex(file: String) {

        var tokenList = arrayListOf<ArrayList<Token>>()
        val lineList = Input().readFile(file)

        lineList.forEach { tokenList.add(Scanner(it).scan()) }

        tokenList.forEach { println(it.toString()) }
    }
}