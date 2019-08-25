package com.github.dkwagner.lexer

enum class Lexeme{

    ID,

    // Literals
    LITERAL_INT,  // integer literal [-0-9][0-9]*
    LITERAL_STRING,  // String literal

    // Keywords
    KEYPHRASE_PLEASE,  // Please (line start)
    KEYPHRASE_ASSIGN,  // equal to (=)
    KEYPHRASE_DECLARE,  // set (var)
    KEYPHRASE_IF,  // check if (if start)
    KEYPHRASE_LOOP,  // loop  (while)
    KEYPHRASE_END_STATEMENT,  // Thanks

    // Operators
    OP_EQUAL,  // is equal to (==)
    OP_NOT_EQUAL,  // does not equal (!=)
    OP_GREATER,  // greater than (>)
    OP_GREATER_OR_EQUAL,  // greater than or equal to
    OP_LESS,  // less than (<)
    OP_LESS_OR_EQUAL,  // less than or equal to
    OP_PLUS,  // plus (+)
    OP_MINUS,  // minus (-)
    OP_MULT,  // times (*)
    OP_DIV,  // over (/)

    END_LINE,  // . (End of statement)

    COMMENT,  // // a comment, disregard entire line
    TODO,

    END_OF_FILE
}

// Please loop while (boolean expression) is true