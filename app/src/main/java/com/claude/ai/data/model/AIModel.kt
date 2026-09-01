package com.claude.ai.data.model

data class AIModel(
    val id: String,
    val name: String,
    val provider: String,
    val description: String,
    val isSelected: Boolean = false
)

enum class ModelType {
    CLAUDE_3_OPUS,
    CLAUDE_3_SONNET,
    CLAUDE_3_HAIKU,
    GPT_4O,
    GEMINI_1_5_PRO,
    LLAMA_3_70B
}
