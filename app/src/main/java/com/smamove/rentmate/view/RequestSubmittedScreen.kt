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
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smamove.rentmate.components.AppBottomNavigation
import com.smamove.rentmate.components.AppButton
import com.smamove.rentmate.components.AppTopHeader
import com.smamove.rentmate.components.StatusBadge
import com.smamove.rentmate.model.UserRole
import com.smamove.rentmate.utils.AppColors

@Composable
fun RequestSubmittedScreen(
    userRole: UserRole,
    onViewStatusClick: () -> Unit,
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

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Check circle
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFEFFDF5)),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(AppColors.PrimaryGreen),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(44.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Request Submitted!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Your landlord has been notified. We’ll keep you\nupdated on the repair.",
                    fontSize = 14.sp,
                    color = AppColors.TextSecondary,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, AppColors.BorderLight)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "Kitchen Tap Leakage",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = AppColors.TextPrimary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        StatusBadge(status = "Submitted")

                        Spacer(modifier = Modifier.height(18.dp))
                        HorizontalDivider(color = AppColors.BorderLight)
                        Spacer(modifier = Modifier.height(18.dp))

                        SubmittedItemRow(label = "Request ID", value = "RM-M1003")
                        Spacer(modifier = Modifier.height(12.dp))
                        SubmittedItemRow(label = "Category", value = "Plumbing")
                        Spacer(modifier = Modifier.height(12.dp))
                        SubmittedItemRow(label = "Priority", value = "Medium")
                        Spacer(modifier = Modifier.height(12.dp))
                        SubmittedItemRow(label = "Property", value = "Green Residency · 204")
                        Spacer(modifier = Modifier.height(12.dp))
                        SubmittedItemRow(label = "Submitted", value = "3 October 2026")
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                AppButton(
                    text = "View Request Status",
                    onClick = onViewStatusClick
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "You can message your landlord at any time.",
                    fontSize = 12.sp,
                    color = AppColors.TextMuted
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun SubmittedItemRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = AppColors.TextSecondary
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = AppColors.TextPrimary
        )
    }
}
