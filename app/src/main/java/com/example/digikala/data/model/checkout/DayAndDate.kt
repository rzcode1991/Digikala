package com.example.digikala.data.model.checkout

import com.google.gson.Gson

data class DayAndDate(
    val day: String,
    val date: Int,
    val month: String
){

    companion object {

        private val gson = Gson()

        fun fromJson(json: String): DayAndDate? {
            return try {
                gson.fromJson(json, DayAndDate::class.java)
            } catch (e: Exception) {
                null
            }
        }

    }

    fun toJson(): String {
        return gson.toJson(this)
    }

}
