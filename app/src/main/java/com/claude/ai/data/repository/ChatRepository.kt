package com.claude.ai.data.repository

import com.claude.ai.data.remote.ApiClient
import com.claude.ai.data.remote.ChatRequest
import com.claude.ai.data.remote.MessageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChatRepository {

    private val apiService = ApiClient.apiService

    suspend fun sendMessage(
        apiKey: String,
        messages: List<Pair<String, String>>,
        model: String = "claude-3-opus-20240229"
    ): Result<String> = withContext(Dispatchers.IO) {
        try {
            val messageRequests = messages.map { (role, content) ->
                MessageRequest(role = role, content = content)
            }

            val request = ChatRequest(
                model = model,
                messages = messageRequests
            )

            val response = apiService.sendMessage(
                apiKey = apiKey,
                request = request
            )

            if (response.isSuccessful) {
                val body = response.body()
                val text = body?.content?.firstOrNull()?.text ?: "لا يوجد استجابة"
                Result.success(text)
            } else {
                val errorBody = response.errorBody()?.string()
                Result.failure(Exception("خطأ: ${response.code()} - $errorBody"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
