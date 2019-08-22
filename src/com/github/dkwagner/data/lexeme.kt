package com.github.dkwagner.data

enum class Lexeme(){
//    COMMENT("//"),
//    WHITESPACE(" "),
//    ID("""^[a-zA-Z_][a-zA-Z0-9_]*$"""),
//    INT("""^[-0-9][0-9]*"""),
//    PLUS("plus"),
//    MINUS("minus"),
//    MULT("times"),
//    DIV("over"),
//    EQUAL("equal to"),
//    PLEASE("Please"),
//    SET("set"),
//    ERROR(""),

    ID,
    L_PAREN,
    R_PAREN,

    // Literals
    INT,
    STRING,

    // Operations
    OP_PLUS,
    OP_MINUS,
    OP_MULT,
    OP_DIV,
    OP_ASSIGN,

    // Keywords
    KEYWORD_PLEASE,
    KEYWORD_DECLARE,
    END_OF_INPUT,


}