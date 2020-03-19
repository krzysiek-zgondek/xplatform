package com.xplatform.xplatformandroid

data class Todo(val title: String, val description: String){
    fun parseToJson(): String{
        return "{\"title\":\"$title\", \"description\":\"$description\" }"
    }
}