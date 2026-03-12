package com.example.rastiapp

data class Task(
    val id: Int,
    val title: String,
    val done: Boolean = false
)