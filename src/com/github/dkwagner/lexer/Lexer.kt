package com.github.dkwagner.lexer

import com.github.dkwagner.lexer.Lexeme.*
import kotlin.system.exitProcess

class Lexer(val input: String) {
    var reservedPhrases = HashMap<Lexeme, String>()  // Hashmap of reserved keywords
    var currentLine = 1  // Current line lexxing
    var tokenStart = 0  // Start of current token
    var position = 0  // current position of pointer
    var currentChar: Char = input[0]  // Grab the first character to begin

    // Setup reserved phrases checker
    init {
        // Keyphrases
        reservedPhrases.put(KEYPHRASE_PLEASE, "Please")
        reservedPhrases.put(KEYPHRASE_IF, "check if")
        reservedPhrases.put(KEYPHRASE_ASSIGN, "equal to")
        reservedPhrases.put(KEYPHRASE_DECLARE, "set")
        reservedPhrases.put(KEYPHRASE_END_STATEMENT, "Thanks")
        reservedPhrases.put(KEYPHRASE_LOOP, "loop while")

        // Operations
        reservedPhrases.put(OP_EQUAL, "is equal to")
        reservedPhrases.put(OP_NOT_EQUAL, "does not equal")
        reservedPhrases.put(OP_GREATER, "is greater than")
        reservedPhrases.put(OP_GREATER_OR_EQUAL, "is greater than or equal to")
        reservedPhrases.put(OP_LESS, "is less than")
        reservedPhrases.put(OP_LESS_OR_EQUAL, "is less than or equal to")
        reservedPhrases.put(OP_PLUS, "plus")
        reservedPhrases.put(OP_MINUS, "minus")
        reservedPhrases.put(OP_MULT, "times")
        reservedPhrases.put(OP_DIV, "over")
    }

    /**
     * Checks for first match in list of possible reserved phrases
     * If none match, assumes ID and returns ID token
     */
    fun lookAhead(possiblePhrases: List<Lexeme>): Token {

        for (lexeme in possiblePhrases) {
            var phrase = reservedPhrases[lexeme]!!  // If lexeme invalid, we want to throw exception
            var phraseLength = phrase.length - 1
            // check that phrase isn't longer than input
            if ((position + phraseLength) >= input.length) {
                continue
            }

            var phraseSubstring = input.substring(position, (position + phraseLength + 1))  // Grab substring of same length as requested phrase

            if (phraseSubstring == phrase) {
                var token = Token(lexeme = lexeme, value = phrase, line = currentLine, pos = tokenStart)
                tokenStart += phraseLength
                position += phraseLength
                getNextChar()
                return token
            }
        }

        return identifier()
    }

    fun stringLiteral(): Token {
        var result = ""

        val literalStart = tokenStart
        val literalLine = currentLine

        while (getNextChar() != '"') {
            if (currentChar == '\u0000') {
                error(currentChar, position, currentLine, LITERAL_STRING)
            } else if (currentChar == '\n') {
                error(currentChar, position, currentLine, LITERAL_STRING)
            }
            result += currentChar
        }
        getNextChar()  // Go to next character for next token
        return Token(lexeme = LITERAL_STRING, value = result, pos = literalStart, line = literalLine)
    }

    fun comment(): Token{
        var endOfComment = false
        while(!endOfComment){
            if(currentChar == '\n' || currentChar == '\u0000'){
                getNextChar()
                return getToken()
            }
            getNextChar()
        }

        return getToken()
    }

    fun numberOrIdentifier(): Token{

        if(currentChar == '-' || currentChar.isDigit()){
            return number()
        }

        return identifier()
    }

    fun number(): Token{

        var number: String
        val line = currentLine
        val pos = tokenStart

        if(currentChar == '-'){
            number = "-"
            getNextChar()
        } else {
            number = ""
        }

        while(currentChar.isDigit()){
            number += currentChar
            getNextChar()
        }

        try{
            number.toInt()
        } catch (e: NumberFormatException){
            error(char = currentChar, errorPos = position, line = currentLine, expectedLexeme = LITERAL_INT)
        }

        return Token(lexeme = LITERAL_INT, value = number, line = line, pos = pos)
    }

    fun identifier(): Token {

        val line = currentLine
        val pos = tokenStart

        var identifier = ""
        while(currentChar.isLetter() || currentChar.isDigit() || currentChar == '_'){
            identifier += currentChar
            getNextChar()
        }

        return Token(lexeme = ID, value = identifier, line = line, pos = pos)
    }

    fun getToken(): Token {
        while (currentChar.isWhitespace()) {
            getNextChar()
        }

        val line = currentLine
        val pos = tokenStart

        when (currentChar) {
            '\u0000' -> return Token(lexeme = END_OF_FILE, value = "", line = line, pos = tokenStart)
            '.' -> {
                getNextChar(); return Token(lexeme = END_LINE, value = ".", line = line, pos = pos)
            }
            '#' -> return comment()
            '"' -> return stringLiteral()
            'P' -> return lookAhead(listOf(KEYPHRASE_PLEASE))
            'c' -> return lookAhead(listOf(KEYPHRASE_IF))
            'e' -> return lookAhead(listOf(KEYPHRASE_ASSIGN))
            's' -> return lookAhead(listOf(KEYPHRASE_DECLARE))
            'T' -> return lookAhead(listOf(KEYPHRASE_END_STATEMENT))
            'l' -> return lookAhead(listOf(KEYPHRASE_LOOP))
            'i' -> return lookAhead(listOf(OP_EQUAL, OP_GREATER_OR_EQUAL, OP_GREATER, OP_LESS_OR_EQUAL, OP_LESS))
            'd' -> return lookAhead(listOf(OP_NOT_EQUAL))
            'p' -> return lookAhead(listOf(OP_PLUS))
            'm' -> return lookAhead(listOf(OP_MINUS))
            't' -> return lookAhead(listOf(OP_MULT))
            'o' -> return lookAhead(listOf(OP_DIV))
        }

        return numberOrIdentifier()
    }

    fun getNextChar(): Char {
        tokenStart++
        position++

        if (position >= input.length) {
            currentChar = '\u0000'
            return currentChar
        }

        currentChar = input[position]
        if (currentChar == '\n') {
            currentLine++
            tokenStart = 0
        }

        return currentChar
    }

    // Print an error and exit process
    fun error(char: Char, errorPos: Int, line: Int, expectedLexeme: Lexeme) {
        println("--- ERROR [LINE: $line, POS: $errorPos] Invalid character $char in expected lexeme $expectedLexeme")
        exitProcess(-1)
    }

    // FOR DEBUGGING
    fun printTokens() {
        var endOfInput = false
        var token = getToken()
        while (token.lexeme != END_OF_FILE) {
            println(token)
            token = getToken()
        }
        println(token)
    }
}