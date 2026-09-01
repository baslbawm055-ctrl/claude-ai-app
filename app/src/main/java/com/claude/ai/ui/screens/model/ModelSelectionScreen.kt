package com.claude.ai.ui.screens.model

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.claude.ai.data.model.AIModel
import com.claude.ai.ui.theme.ClaudeOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelSelectionScreen(
    onBack: () -> Unit,
    onModelSelected: (String) -> Unit
) {
    var selectedModel by remember { mutableStateOf("claude-3-opus") }

    val claudeModels = remember {
        listOf(
            AIModel("claude-3-opus", "Claude 3 Opus", "Anthropic", "الأكثر ذكاءً والقادر على التعقيد المعقد", true),
            AIModel("claude-3-sonnet", "Claude 3 Sonnet", "Anthropic", "مثالي لمعظم المهام"),
            AIModel("claude-3-haiku", "Claude 3 Haiku", "Anthropic", "الأسرع والأكثر كفاءة للمهام البسيطة")
        )
    }

    val otherModels = remember {
        listOf(
            AIModel("gpt-4o", "GPT-4o", "OpenAI", ""),
            AIModel("gemini-1.5-pro", "Gemini 1.5 Pro", "Google", ""),
            AIModel("llama-3-70b", "Llama 3 70B", "Meta", "")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("النماذج") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "رجوع"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Header
            item {
                Text(
                    text = "اختر النموذج المناسب لاحتياجاتك",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Claude Models
            item {
                Text(
                    text = "Claude",
                    style = MaterialTheme.typography.titleMedium,
                    color = ClaudeOrange
                )
            }

            items(claudeModels) { model ->
                ModelCard(
                    model = model,
                    isSelected = selectedModel == model.id,
                    onSelect = {
                        selectedModel = model.id
                        onModelSelected(model.id)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Other Models
            item {
                Text(
                    text = "نماذج أخرى",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            items(otherModels) { model ->
                ModelCard(
                    model = model,
                    isSelected = selectedModel == model.id,
                    onSelect = {
                        selectedModel = model.id
                        onModelSelected(model.id)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelCard(
    model: AIModel,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Card(
        onClick = onSelect,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) ClaudeOrange.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface
        ),
        border = if (isSelected) CardDefaults.outlinedCardBorder().takeIf { true } else null
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = model.name,
                    style = MaterialTheme.typography.titleMedium
                )
                if (model.description.isNotEmpty()) {
                    Text(
                        text = model.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Text(
                    text = model.provider,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "محدد",
                    tint = ClaudeOrange
                )
            }
        }
    }
}
