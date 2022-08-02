package com.example.quizquiz.database

data class Quiz
    (
    // 퀴즈의 종류(OX, N지선다)
    var type: String?,
    // 발문
    var question: String?,
    // 정답
    var answer: String?,
    // 퀴즈의 카테고리
    var category: String?,
    // N지선다 문제의 선택지
    var guesses: List<String>? = null
)