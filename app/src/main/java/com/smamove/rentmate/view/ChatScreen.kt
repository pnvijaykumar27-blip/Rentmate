package com.smamove.rentmate.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smamove.rentmate.components.AppBottomNavigation
import com.smamove.rentmate.model.ChatMessage
import com.smamove.rentmate.model.UserRole
import com.smamove.rentmate.utils.AppColors

@Composable
fun ChatScreen(
    messages: List<ChatMessage>,
    userRole: UserRole,
    onSendMessage: (String) -> Unit,
    onBackClick: () -> Unit,
    onNavigate: (String) -> Unit
) {
    var inputText by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            // Chat Top Bar
            Surface(
                shadowElevation = 2.dp,
                color = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = AppColors.TextPrimary
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFEFFDF5)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "RM",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = AppColors.PrimaryGreen
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Rajesh Mehta",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = AppColors.TextPrimary
                            )
                            Text(
                                text = "Landlord · Green Residency",
                                fontSize = 12.sp,
                                color = AppColors.TextSecondary
                            )
                        }
                    }

                    IconButton(onClick = { /* Call */ }) {
                        Icon(
                            imageVector = Icons.Outlined.Call,
                            contentDescription = "Call",
                            tint = AppColors.PrimaryGreen
                        )
                    }
                }
            }
        },
        bottomBar = {
            AppBottomNavigation(
                currentRoute = "messages",
                userRole = userRole,
                onNavigate = onNavigate
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Issue Ticket Banner
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .clickable { onNavigate("maintenance_status") },
                shape = RoundedCornerShape(14.dp),
                color = Color(0xFFEFFDF5)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.WaterDrop,
                            contentDescription = null,
                            tint = AppColors.PrimaryGreen,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Kitchen Tap Leakage",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = AppColors.TextPrimary
                            )
                            Text(
                                text = "RM-M1003 · In Progress",
                                fontSize = 11.sp,
                                color = AppColors.TextSecondary
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                        contentDescription = null,
                        tint = AppColors.PrimaryGreen
                    )
                }
            }

            // Date separator
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "6 October 2026",
                    fontSize = 12.sp,
                    color = AppColors.TextMuted
                )
            }

            // Messages List
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(messages) { msg ->
                    ChatBubble(message = msg)
                }
            }

            // Input Bar
            Surface(
                shadowElevation = 4.dp,
                color = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        placeholder = { Text("Type a message...", color = AppColors.TextMuted, fontSize = 14.sp) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.AttachFile,
                                contentDescription = "Attach",
                                tint = AppColors.TextSecondary
                            )
                        },
                        shape = RoundedCornerShape(24.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = AppColors.InputBackground,
                            unfocusedContainerColor = AppColors.InputBackground,
                            focusedBorderColor = AppColors.PrimaryGreen,
                            unfocusedBorderColor = AppColors.BorderLight,
                            focusedTextColor = AppColors.TextPrimary,
                            unfocusedTextColor = AppColors.TextPrimary
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    IconButton(
                        onClick = {
                            if (inputText.isNotBlank()) {
                                onSendMessage(inputText)
                                inputText = ""
                            }
                        },
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(AppColors.PrimaryGreen)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Send",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ChatBubble(message: ChatMessage) {
    val isMe = message.isFromMe
    val alignment = if (isMe) Alignment.End else Alignment.Start
    val bgColor = if (isMe) Color(0xFFEFFDF5) else Color(0xFFF3F4F6)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment
    ) {
        Surface(
            shape = RoundedCornerShape(
                topStart = 18.dp,
                topEnd = 18.dp,
                bottomStart = if (isMe) 18.dp else 4.dp,
                bottomEnd = if (isMe) 4.dp else 18.dp
            ),
            color = bgColor,
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = message.text,
                    fontSize = 14.sp,
                    color = AppColors.TextPrimary,
                    lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.align(Alignment.End),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = message.time,
                        fontSize = 11.sp,
                        color = AppColors.TextMuted
                    )
                    if (isMe) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "· Read",
                            fontSize = 11.sp,
                            color = AppColors.TextSecondary
                        )
                    }
                }
            }
        }
    }
}
