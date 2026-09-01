package com.claude.ai.data.remote

import com.google.gson.annotations.SerializedName

data class ChatRequest(
    @SerializedName("model")
    val model: String,

    @SerializedName("messages")
    val messages: List<MessageRequest>,

    @SerializedName("max_tokens")
    val maxTokens: Int = 4096
)

data class MessageRequest(
    @SerializedName("role")
    val role: String,

    @SerializedName("content")
    val content: String
)

data class ChatResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("content")
    val content: List<ContentResponse>,

    @SerializedName("model")
    val model: String,

    @SerializedName("stop_reason")
    val stopReason: String?
)

data class ContentResponse(
    @SerializedName("type")
    val type: String,

    @SerializedName("text")
    val text: String
)

data class ErrorResponse(
    @SerializedName("error")
    val error: ErrorDetail
)

data class ErrorDetail(
    @SerializedName("type")
    val type: String,

    @SerializedName("message")
    val message: String
)
