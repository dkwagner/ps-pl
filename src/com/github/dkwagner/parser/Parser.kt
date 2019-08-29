package com.github.dkwagner.parser

import com.github.dkwagner.lexer.Lexeme.*
import com.github.dkwagner.lexer.Lexer
import com.github.dkwagner.lexer.Token
import java.io.File
import kotlin.system.measureTimeMillis

class Parser {

    fun parseFile(filePath: String){

        var tokens: List<Token>
        var variables: List<Token>
        var lexer = Lexer(File(filePath).readText())  // Read file from filepath into lexer

        val executionTime = measureTimeMillis { tokens = getTokens(lexer) }   // Get all tokens
        variables = getVariableDeclarations(tokens)

        variables.forEach { println(it) }

    }

    fun getVariableDeclarations(tokens: List<Token>): List<Token>{
        var variables = mutableListOf<Token>()

        tokens.forEachIndexed {index, token ->
            if(token.lexeme == ID){
                if(index - 1 >= 0 && tokens[index - 1].lexeme == KEYPHRASE_DECLARE){
                    variables.add(token)
                }
            }
        }

        return variables
    }

    fun getTokens(lexer: Lexer): List<Token>{

        var tokens = mutableListOf<Token>()

        var currentToken =  lexer.getToken()
        while(currentToken.lexeme != END_OF_FILE){
            tokens.add(currentToken)
            currentToken = lexer.getToken()
        }
        tokens.add(currentToken)

        return tokens
    }
}