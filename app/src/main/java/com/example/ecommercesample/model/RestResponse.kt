package com.example.ecommercesample.model

data class Item(
    val title: String,
    val image: String
)

data class Section(
    val sectionType: String,
    val items: List<Item>
)
