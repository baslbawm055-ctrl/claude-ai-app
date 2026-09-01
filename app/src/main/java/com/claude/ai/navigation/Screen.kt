package com.claude.ai.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Chat : Screen("chat/{chatId}") {
        fun createRoute(chatId: String = "new") = "chat/$chatId"
    }
    object Projects : Screen("projects")
    object Chats : Screen("chats")
    object Settings : Screen("settings")
    object ModelSelection : Screen("model_selection")
    object Profile : Screen("profile")
}
