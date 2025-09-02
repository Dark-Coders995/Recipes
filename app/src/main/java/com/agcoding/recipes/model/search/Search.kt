package com.agcoding.recipes.model.search

data class Search(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)