package com.github.dkwagner.extension

import com.github.dkwagner.data.Lexeme
import com.github.dkwagner.data.Lexeme.*

/**
 * Get the lexeme this string represents, otherwise return error
 */
fun String.getLexeme(): Lexeme {

    val idRegex = Regex(ID.value)
    val intRegex = Regex(INT.value)

    when(this){
            COMMENT.value -> return COMMENT
            PLEASE.value -> return PLEASE
            PLUS.value -> return PLUS
            MINUS.value -> return MINUS
            MULT.value -> return MULT
            DIV.value -> return DIV
            EQUAL.value -> return EQUAL
            TO.value -> return TO
            SET.value -> return SET
        }

    if(intRegex.matches(this)){
        return INT
    }

    if(idRegex.matches(this)){
        return ID
    }

    if(length >= 2){
        if(subSequence(0, 1).equals(COMMENT.value)){
            return COMMENT
        }
    }

    return ERROR
}