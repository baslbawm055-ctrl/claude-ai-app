package com.claude.ai.data.model

data class Chat(
    val id: String,
    val title: String,
    val lastMessage: String,
    val timestamp: Long,
    val unreadCount: Int = 0
)
