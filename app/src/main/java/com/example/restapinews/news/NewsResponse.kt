package com.example.restapinews.news



data class NewsResponse(
    val news: List<News>,
    val page: Int,
    val status: String
)