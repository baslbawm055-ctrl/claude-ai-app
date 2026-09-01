package com.claude.ai.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.claude.ai.data.model.Message
import com.claude.ai.data.repository.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val repository = ChatRepository()

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val conversationHistory = mutableListOf<Pair<String, String>>()

    fun sendMessage(content: String, apiKey: String, model: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            // Add user message
            val userMessage = Message(
                id = System.currentTimeMillis().toString(),
                content = content,
                isUser = true
            )
            _messages.value = _messages.value + userMessage
            conversationHistory.add("user" to content)

            // Send to API
            val result = repository.sendMessage(
                apiKey = apiKey,
                messages = conversationHistory,
                model = model
            )

            result.fold(
                onSuccess = { response ->
                    val assistantMessage = Message(
                        id = System.currentTimeMillis().toString(),
                        content = response,
                        isUser = false
                    )
                    _messages.value = _messages.value + assistantMessage
                    conversationHistory.add("assistant" to response)
                },
                onFailure = { e ->
                    _error.value = e.message
                }
            )

            _isLoading.value = false
        }
    }

    fun clearError() {
        _error.value = null
    }
}
