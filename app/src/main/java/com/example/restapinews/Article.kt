package com.example.restapinews

data class Article(
    val source: Map<String, String>,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

