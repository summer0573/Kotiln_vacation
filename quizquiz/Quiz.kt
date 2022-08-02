package com.example.quizquiz.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity(tableName = "quiz")
data class Quiz
    (
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    // 퀴즈의 종류(OX, N지선다)
    var type: String?,
    // 발문
    var question: String?,
    // 정답
    var answer: String?,
    // 퀴즈의 카테고리
    var category: String?,
    // N지선다 문제의 선택지
    @TypeConverters(StringListTypeConverter::class)
    var guesses: List<String>? = null
)

class StringListTypeConverter {
    @TypeConverter
    fun stringListToString(stringList: List<String>?): String? {
        return stringList?.joinToString(",")
    }
    @TypeConverter
    fun stringToStringList(string: String?): List<String>? {
        return string?.split(",")?.toList()
    }
}