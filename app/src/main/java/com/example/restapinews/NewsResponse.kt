package com.example.restapinews

data class NewsResponse(val status: String, val totalResults: Int, val articles: List<Article>)