package com.smamove.rentmate.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
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
import com.smamove.rentmate.components.AppTextField
import com.smamove.rentmate.components.AppTopHeader
import com.smamove.rentmate.model.Conversation
import com.smamove.rentmate.model.UserRole
import com.smamove.rentmate.utils.AppColors

@Composable
fun MessagesScreen(
    conversations: List<Conversation>,
    userRole: UserRole,
    onConversationClick: (Conversation) -> Unit,
    onNavigate: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color.White,
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
                .verticalScroll(rememberScrollState())
        ) {
            AppTopHeader()

            Spacer(modifier = Modifier.height(8.dp))

            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = "Messages",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Stay connected with your landlord.",
                    fontSize = 14.sp,
                    color = AppColors.TextSecondary
                )

                Spacer(modifier = Modifier.height(20.dp))

                AppTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = "",
                    placeholder = "Search messages",
                    leadingIcon = Icons.Outlined.Search
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Your conversations",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.TextPrimary
                    )
                    Text(
                        text = "2 unread",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AppColors.PrimaryGreen
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                conversations.forEach { conv ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onConversationClick(conv) }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFEFFDF5)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = conv.initials,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = AppColors.PrimaryGreen
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = conv.name,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AppColors.TextPrimary
                                )
                                Text(
                                    text = conv.time,
                                    fontSize = 12.sp,
                                    color = AppColors.TextMuted
                                )
                            }

                            Spacer(modifier = Modifier.height(2.dp))

                            Text(
                                text = conv.category,
                                fontSize = 12.sp,
                                color = AppColors.PrimaryGreen,
                                fontWeight = FontWeight.Medium
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = conv.lastMessage,
                                    fontSize = 13.sp,
                                    color = AppColors.TextSecondary,
                                    maxLines = 1,
                                    modifier = Modifier.weight(1f)
                                )

                                if (conv.unreadCount > 0) {
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Box(
                                        modifier = Modifier
                                            .size(20.dp)
                                            .clip(CircleShape)
                                            .background(AppColors.PrimaryGreen),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = conv.unreadCount.toString(),
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                    }
                                }
                            }
                        }
                    }
                    HorizontalDivider(color = AppColors.BorderLight)
                }

                Spacer(modifier = Modifier.height(24.dp))

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFFEFFDF5)
                ) {
                    Text(
                        text = "All conversations are linked to your rental at Green Residency, Flat 204.",
                        fontSize = 13.sp,
                        color = AppColors.TextSecondary,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
