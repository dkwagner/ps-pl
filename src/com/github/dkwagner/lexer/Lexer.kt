package com.github.dkwagner.lexer

import com.github.dkwagner.lexer.Lexeme.*
import kotlin.system.exitProcess

class Lexer(val input: String) {
    var keyphrases = HashMap<String, Lexeme>()  // Hashmap of reserved keywords
    var currentLine = 1  // Current line lexxing
    var tokenStart = 0  // Start of current token
    var position = 0  // current position of pointer
    var currentChar: Char = input[0]  // Grab the first character to begin

    // Setup keyphrases checker
    init {
        keyphrases.put("Please", KEYPHRASE_PLEASE)
        keyphrases.put("if", KEYPHRASE_IF)
        keyphrases.put("equal to", KEYPHRASE_ASSIGN)
        keyphrases.put("set", KEYPHRASE_DECLARE)
        keyphrases.put("Thanks", KEYPHRASE_END_STATEMENT)
        keyphrases.put("loop while", KEYPHRASE_LOOP)
    }

    fun stringLiteral(): Token{
        var result = ""

        val literalStart = tokenStart
        val literalLine = currentLine

        while(getNextChar() != '"'){
            if(currentChar == '\u0000'){
                error(currentChar, position, currentLine, LITERAL_STRING)
            } else if (currentChar == '\n'){
                error(currentChar, position, currentLine, LITERAL_STRING)
            }
            result += currentChar
        }
        getNextChar()  // Go to next character for next token
        return Token(lexeme = LITERAL_STRING, value = result, pos = literalStart, line = literalLine)
    }

    fun getToken(): Token{
        while(currentChar.isWhitespace()){
            getNextChar()
        }

        val char = currentChar

        when(currentChar){
            '\u0000' -> return Token(lexeme = END_OF_FILE, value = "", line = currentLine, pos = tokenStart)
            '.' -> { getNextChar(); return Token(lexeme = END_LINE, value = ".", line = currentLine, pos = tokenStart) }
            '"' -> { getNextChar(); return stringLiteral() }
        }

        getNextChar()
        return Token(lexeme = TODO, line = currentLine, pos = tokenStart, value = char.toString())  // TODO: Implement more tokens
    }

    fun getNextChar(): Char{
        tokenStart ++
        position ++

        if(position >= input.length){
            currentChar = '\u0000'
            return currentChar
        }
        currentChar = input[position]
        if(currentChar == '\n'){
            currentLine ++
            tokenStart = 0
        }
        return currentChar
    }

    // Print an error and exit process
    fun error(char: Char, errorPos: Int, line: Int, expectedLexeme: Lexeme){
        println("--- ERROR [LINE: $line, POS: $errorPos] Invalid character $char in expected lexeme $expectedLexeme")
        exitProcess(-1)
    }


    // FOR DEBUGGING
    fun printTokens(){
        var endOfInput = false
        var token = getToken()
        while(token.lexeme != END_OF_FILE){
            println(token)
            token = getToken()
        }
    }
}