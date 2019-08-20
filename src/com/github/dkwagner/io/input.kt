package com.github.dkwagner.io

import java.io.File
import java.nio.charset.Charset

class Input {

    fun readFile(file: String): ArrayList<String> {

        val file = File(file)

        var linesList = arrayListOf<String>()

        file.useLines { lines -> linesList.addAll(lines)}

        return linesList
    }

}