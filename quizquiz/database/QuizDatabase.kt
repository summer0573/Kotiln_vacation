package com.example.quizquiz.database

import androidx.room.*

//Data Access Object
@Dao
interface QuizDao {
    @Insert
    fun insert(quiz : Quiz) : Long
    @Update
    fun update(quiz : Quiz)
    @Delete
    fun delete(quiz : Quiz)
    @Query("SELECT * FROM quiz")
    fun getAll() : List<Quiz>
}

class QuizDatabase {

}