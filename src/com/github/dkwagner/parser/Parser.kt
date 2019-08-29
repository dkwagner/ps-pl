package com.github.dkwagner.parser

import com.github.dkwagner.lexer.Lexeme.*
import com.github.dkwagner.lexer.Lexer
import com.github.dkwagner.lexer.Token
import java.io.File

class Parser {

    fun parseFile(filePath: String){

        var tokens: List<Token>
        var variables: Map<String, Token>
        var lexer = Lexer(File(filePath).readText())  // Read file from filepath into lexer

        tokens = getTokens(lexer)   // Get all tokens
        variables = getVariableDeclarations(tokens)

        variables.forEach { println(it.value) }

    }

    fun getVariableDeclarations(tokens: List<Token>): Map<String, Token>{
        var variables = mutableMapOf<String, Token>()

        tokens.forEachIndexed {index, token ->
            if(token.lexeme == ID){
                if(index - 1 >= 0 && tokens[index - 1].lexeme == KEYPHRASE_DECLARE){
                    if(!variables.containsKey(token.value)){
                        variables[token.value] = token
                    }
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