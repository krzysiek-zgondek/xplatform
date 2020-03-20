package com.xplatform.xplatformandroid.dto

import com.google.gson.Gson

data class Todo(val title: String, val description: String) {

    fun parseToJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        @JvmStatic
        fun fromJson(json: String): Todo {
            return Gson().fromJson(json, Todo::class.java)
        }
    }
}