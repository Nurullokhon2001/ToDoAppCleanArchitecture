package com.example.to_doappcleanarchitecture.data.converter

import androidx.room.TypeConverter
import com.example.to_doappcleanarchitecture.domain.model.Priority

object Converter {

    @TypeConverter
    fun fromPriority(priority: Priority): String = priority.name

    @TypeConverter
    fun toPriority(priority: String): Priority = Priority.valueOf(priority)
}