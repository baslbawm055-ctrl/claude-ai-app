package com.claude.ai.data.model

data class Project(
    val id: String,
    val name: String,
    val description: String,
    val chatCount: Int,
    val timestamp: Long
)
