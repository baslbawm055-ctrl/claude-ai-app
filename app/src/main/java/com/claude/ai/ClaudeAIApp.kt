package com.claude.ai

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.claude.ai.navigation.Screen
import com.claude.ai.ui.components.NavigationDrawer
import com.claude.ai.ui.screens.chat.ChatScreen
import com.claude.ai.ui.screens.chats.ChatsScreen
import com.claude.ai.ui.screens.home.HomeScreen
import com.claude.ai.ui.screens.model.ModelSelectionScreen
import com.claude.ai.ui.screens.projects.ProjectsScreen
import com.claude.ai.ui.screens.settings.SettingsScreen
import com.claude.ai.ui.theme.ClaudeOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClaudeAIApp() {
    val navController = rememberNavController()
    var isDrawerOpen by remember { mutableStateOf(false) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showTopBar = currentRoute in listOf(
        Screen.Home.route,
        Screen.Projects.route,
        Screen.Chats.route
    )

    NavigationDrawer(
        isOpen = isDrawerOpen,
        onClose = { isDrawerOpen = false },
        onNewChat = { navController.navigate(Screen.Chat.createRoute()) },
        onProjects = {
            navController.navigate(Screen.Projects.route) {
                popUpTo(Screen.Home.route) { inclusive = true }
            }
        },
        onChats = {
            navController.navigate(Screen.Chats.route) {
                popUpTo(Screen.Home.route) { inclusive = true }
            }
        },
        onSettings = { navController.navigate(Screen.Settings.route) },
        onModelSelection = { navController.navigate(Screen.ModelSelection.route) },
        onLogout = { /* Handle logout */ }
    )

    Scaffold(
        topBar = {
            if (showTopBar) {
                TopAppBar(
                    title = { },
                    navigationIcon = {
                        IconButton(onClick = { isDrawerOpen = true }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "القائمة"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onNewChat = { navController.navigate(Screen.Chat.createRoute()) },
                    onSettings = { navController.navigate(Screen.Settings.route) }
                )
            }

            composable(
                route = Screen.Chat.route,
                arguments = listOf(
                    navArgument("chatId") {
                        type = NavType.StringType
                        defaultValue = "new"
                    }
                )
            ) { backStackEntry ->
                val chatId = backStackEntry.arguments?.getString("chatId")
                ChatScreen(
                    chatId = chatId,
                    onBack = { navController.popBackStack() }
                )
            }

            composable(Screen.Projects.route) {
                ProjectsScreen(
                    onProjectClick = { /* Navigate to project */ },
                    onNewProject = { /* Create new project */ }
                )
            }

            composable(Screen.Chats.route) {
                ChatsScreen(
                    onChatClick = { chatId ->
                        navController.navigate(Screen.Chat.createRoute(chatId))
                    },
                    onNewChat = { navController.navigate(Screen.Chat.createRoute()) }
                )
            }

            composable(Screen.Settings.route) {
                SettingsScreen(
                    onBack = { navController.popBackStack() }
                )
            }

            composable(Screen.ModelSelection.route) {
                ModelSelectionScreen(
                    onBack = { navController.popBackStack() },
                    onModelSelected = { modelId ->
                        // Handle model selection
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
