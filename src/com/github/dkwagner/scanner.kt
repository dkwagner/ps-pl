package com.github.dkwagner

import com.github.dkwagner.data.Lexeme.*
import com.github.dkwagner.data.Token
import com.github.dkwagner.extension.getLexeme

class Scanner(val input: String) {

    /**
     * Scan the input string and convert it into tokens
     */
    fun scanV1(): ArrayList<Token> {

        var tokenizedLine = arrayListOf<Token>()

        var currentIndex = 0

        var chunk = ""

        while (currentIndex < input.length) {
            var char = input[currentIndex]
            if (isWhitespace(char)) {  // Whitespace case
                if (chunk != "") {
                    var token = chunk.getLexeme()
                    tokenizedLine.add(Token(lexeme = token, value = chunk))
                    chunk = ""  // set chunk back to empty

                    // If we encounter a comment, dont parse the rest of the line
                    if (token.equals(COMMENT)) {
                        return tokenizedLine
                    }
                }

                tokenizedLine.add(Token(lexeme = WHITESPACE, value = " "))
                currentIndex++
            } else if (currentIndex == input.lastIndex) {  // End of line case
                chunk = "$chunk$char"
                var token = chunk.getLexeme()
                tokenizedLine.add(Token(lexeme = token, value = chunk))
                chunk = ""  // set chunk back to empty

                // If we encounter a comment, dont parse the rest of the line
                if (token.equals(COMMENT)) {
                    return tokenizedLine
                }
                currentIndex++

            } else {
                chunk = "$chunk$char"
                currentIndex++
            }
        }

        return tokenizedLine
    }

    fun isWhitespace(c: Char): Boolean {
        return c.equals(' ')
    }

}