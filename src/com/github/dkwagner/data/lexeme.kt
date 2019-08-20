package com.github.dkwagner.data

enum class Lexeme(val value: String){
    COMMENT("//"),
    WHITESPACE(" "),
    ID("""^[a-zA-Z_][a-zA-Z0-9_]*$"""),
    INT("""^[-0-9][0-9]*"""),
    PLUS("plus"),
    MINUS("minus"),
    MULT("times"),
    DIV("over"),
    EQUAL("equal"),
    TO("to"),
    PLEASE("Please"),
    SET("set"),
    ERROR(""),

    // Composite Lexemes
    ASSIGN(EQUAL.value + WHITESPACE.value + TO.value)

}