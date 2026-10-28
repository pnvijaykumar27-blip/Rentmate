package com.smamove.rentmate.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smamove.rentmate.components.AppBottomNavigation
import com.smamove.rentmate.components.AppOutlinedButton
import com.smamove.rentmate.components.AppTopHeader
import com.smamove.rentmate.components.StatusBadge
import com.smamove.rentmate.model.MaintenanceRequest
import com.smamove.rentmate.model.UserRole
import com.smamove.rentmate.utils.AppColors

@Composable
fun MaintenanceStatusScreen(
    request: MaintenanceRequest,
    userRole: UserRole,
    onMessageLandlordClick: () -> Unit,
    onBackClick: () -> Unit,
    onNavigate: (String) -> Unit
) {
    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            AppBottomNavigation(
                currentRoute = "maintenance",
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
            AppTopHeader(onBackClick = onBackClick)

            Spacer(modifier = Modifier.height(8.dp))

            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = "Request Status",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${request.id} · Green Residency, Flat 204",
                    fontSize = 14.sp,
                    color = AppColors.TextSecondary
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Card info
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, AppColors.BorderLight)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = request.title,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AppColors.TextPrimary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "${request.category} · ${request.priority}",
                                    fontSize = 13.sp,
                                    color = AppColors.TextSecondary
                                )
                            }
                            StatusBadge(status = request.status)
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = request.description,
                            fontSize = 14.sp,
                            color = AppColors.TextSecondary,
                            lineHeight = 20.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "Request timeline",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Timeline Step 1
                TimelineItem(
                    isCompleted = true,
                    title = "Request submitted",
                    time = "3 October 2026 · 9:15 AM",
                    desc = "Your landlord received the issue details.",
                    showLine = true
                )

                // Timeline Step 2
                TimelineItem(
                    isCompleted = true,
                    title = "Request acknowledged",
                    time = "4 October 2026 · 10:30 AM",
                    desc = "Rajesh Mehta is arranging a plumber.",
                    showLine = true
                )

                // Timeline Step 3
                TimelineItem(
                    isCompleted = true,
                    title = "Repair scheduled",
                    time = "6 October 2026 · 4:20 PM",
                    desc = "Plumber confirmed for 7 October, 10–11 AM.",
                    showLine = true
                )

                // Timeline Step 4
                TimelineItem(
                    isCompleted = false,
                    title = "Visit & resolution",
                    time = "7 October 2026 · 10–11 AM",
                    desc = "Upcoming · Please be available at home.",
                    showLine = false
                )

                Spacer(modifier = Modifier.height(28.dp))

                AppOutlinedButton(
                    text = "Message Landlord",
                    icon = Icons.Outlined.ChatBubbleOutline,
                    onClick = onMessageLandlordClick
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun TimelineItem(
    isCompleted: Boolean,
    title: String,
    time: String,
    desc: String,
    showLine: Boolean
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(if (isCompleted) Color(0xFFEFFDF5) else AppColors.InputBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isCompleted) Icons.Outlined.Check else Icons.Outlined.Schedule,
                    contentDescription = null,
                    tint = if (isCompleted) AppColors.PrimaryGreen else AppColors.TextMuted,
                    modifier = Modifier.size(16.dp)
                )
            }

            if (showLine) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(50.dp)
                        .background(AppColors.BorderLight)
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.padding(bottom = if (showLine) 16.dp else 0.dp)) {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.TextPrimary
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = time,
                fontSize = 12.sp,
                color = AppColors.TextMuted
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = desc,
                fontSize = 13.sp,
                color = AppColors.TextSecondary
            )
        }
    }
}
