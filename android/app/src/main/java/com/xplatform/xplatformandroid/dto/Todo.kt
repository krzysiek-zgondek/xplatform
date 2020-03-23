package com.xplatform.xplatformandroid.dto

import com.google.gson.Gson
import java.io.Serializable

data class Todo(val title: String, val description: String) : Serializable {
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