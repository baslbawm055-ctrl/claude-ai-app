package com.claude.ai.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.claude.ai.ui.theme.ClaudeOrange

@Composable
fun NavigationDrawer(
    isOpen: Boolean,
    onClose: () -> Unit,
    onNewChat: () -> Unit,
    onProjects: () -> Unit,
    onChats: () -> Unit,
    onSettings: () -> Unit,
    onModelSelection: () -> Unit,
    onLogout: () -> Unit
) {
    if (isOpen) {
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet(
                    modifier = Modifier.width(300.dp)
                ) {
                    // Header
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Surface(
                                modifier = Modifier.size(48.dp),
                                shape = MaterialTheme.shapes.medium,
                                color = ClaudeOrange
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "أ",
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "أحمد",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "ahmed@example.com",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Search
                    OutlinedTextField(
                        value = "",
                        onValueChange = { },
                        placeholder = { Text("بحث في المحادثات") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Menu Items
                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Add, contentDescription = null) },
                        label = { Text("محادثة جديدة") },
                        selected = false,
                        onClick = {
                            onNewChat()
                            onClose()
                        }
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Folder, contentDescription = null) },
                        label = { Text("المشروعات") },
                        selected = false,
                        onClick = {
                            onProjects()
                            onClose()
                        }
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.ChatBubble, contentDescription = null) },
                        label = { Text("المحادثات") },
                        selected = false,
                        onClick = {
                            onChats()
                            onClose()
                        }
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Star, contentDescription = null) },
                        label = { Text("المفضلة") },
                        selected = false,
                        onClick = { onClose() }
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.History, contentDescription = null) },
                        label = { Text("الأرشيف") },
                        selected = false,
                        onClick = { onClose() }
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                        label = { Text("الإعدادات") },
                        selected = false,
                        onClick = {
                            onSettings()
                            onClose()
                        }
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.SmartToy, contentDescription = null) },
                        label = { Text("النماذج") },
                        selected = false,
                        onClick = {
                            onModelSelection()
                            onClose()
                        }
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Help, contentDescription = null) },
                        label = { Text("مساعدة") },
                        selected = false,
                        onClick = { onClose() }
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Logout, contentDescription = null) },
                        label = { Text("تسجيل الخروج") },
                        selected = false,
                        onClick = {
                            onLogout()
                            onClose()
                        }
                    )
                }
            },
            drawerState = rememberDrawerState(
                initialValue = if (isOpen) DrawerState.OpenValue else DrawerState.ClosedValue
            ),
            gesturesEnabled = isOpen
        ) {
            // Empty content
        }
    }
}
