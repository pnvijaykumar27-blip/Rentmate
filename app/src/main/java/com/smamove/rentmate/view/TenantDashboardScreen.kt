package com.smamove.rentmate.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smamove.rentmate.components.AppBottomNavigation
import com.smamove.rentmate.components.AppButton
import com.smamove.rentmate.components.AppTopHeader
import com.smamove.rentmate.model.User
import com.smamove.rentmate.model.UserRole
import com.smamove.rentmate.utils.AppColors

@Composable
fun TenantDashboardScreen(
    user: User,
    onNavigate: (String) -> Unit
) {
    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            AppBottomNavigation(
                currentRoute = "dashboard",
                userRole = UserRole.TENANT,
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
                    text = "Good Morning,\n${user.name.ifBlank { "[Tenant Name]" }}",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary,
                    lineHeight = 34.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Main Residence Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFEFFDF5)
                    )
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.White),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Home,
                                    contentDescription = null,
                                    tint = AppColors.PrimaryGreen,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = user.propertyName.ifBlank { "Green Residency" },
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AppColors.TextPrimary
                                )
                                Text(
                                    text = user.unitDetails.ifBlank { "Flat 204, Baner Road, Pune" },
                                    fontSize = 13.sp,
                                    color = AppColors.TextSecondary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = "Monthly Rent",
                                    fontSize = 12.sp,
                                    color = AppColors.TextSecondary
                                )
                                Row(verticalAlignment = Alignment.Bottom) {
                                    Text(
                                        text = user.monthlyRent.ifBlank { "₹15,000" },
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = AppColors.TextPrimary
                                    )
                                    Text(
                                        text = " / month",
                                        fontSize = 13.sp,
                                        color = AppColors.TextSecondary,
                                        modifier = Modifier.padding(bottom = 3.dp)
                                    )
                                }
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "Next Due Date",
                                    fontSize = 12.sp,
                                    color = AppColors.TextSecondary
                                )
                                Text(
                                    text = "Due: 5 October",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AppColors.TextPrimary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .background(AppColors.PrimaryGreen, CircleShape)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Status: Payment Due",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = AppColors.TextPrimary
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        AppButton(
                            text = "Pay Rent",
                            onClick = { onNavigate("pay_rent") }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                // Section: Your rental at a glance
                Text(
                    text = "Your rental at a glance",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    GlanceCard(
                        icon = Icons.Outlined.AccountBalanceWallet,
                        label = "Rent Status",
                        mainVal = "Due",
                        subText = "October rent",
                        modifier = Modifier.weight(1f),
                        onClick = { onNavigate("payments") }
                    )
                    GlanceCard(
                        icon = Icons.Outlined.Build,
                        label = "Maintenance Requests",
                        mainVal = "1 open",
                        subText = "In progress",
                        modifier = Modifier.weight(1.1f),
                        onClick = { onNavigate("maintenance") }
                    )
                    GlanceCard(
                        icon = Icons.Outlined.Description,
                        label = "Agreement Status",
                        mainVal = "Active",
                        subText = "Until Sep 2027",
                        modifier = Modifier.weight(1f),
                        onClick = { onNavigate("profile") }
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                // Section: Quick actions
                Text(
                    text = "Quick actions",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    QuickActionButton(
                        icon = Icons.Outlined.AccountBalanceWallet,
                        label = "Pay Rent",
                        onClick = { onNavigate("pay_rent") }
                    )
                    QuickActionButton(
                        icon = Icons.Outlined.ChatBubbleOutline,
                        label = "Messages",
                        onClick = { onNavigate("messages") }
                    )
                    QuickActionButton(
                        icon = Icons.Outlined.Build,
                        label = "Maintenance",
                        onClick = { onNavigate("maintenance") }
                    )
                    QuickActionButton(
                        icon = Icons.Outlined.Description,
                        label = "Agreement",
                        onClick = { onNavigate("profile") }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun GlanceCard(
    icon: ImageVector,
    label: String,
    mainVal: String,
    subText: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, AppColors.BorderLight)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = AppColors.PrimaryGreen,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                fontSize = 11.sp,
                color = AppColors.TextSecondary,
                lineHeight = 13.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = mainVal,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.TextPrimary
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subText,
                fontSize = 11.sp,
                color = AppColors.TextMuted
            )
        }
    }
}

@Composable
private fun QuickActionButton(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(Color(0xFFEFFDF5)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = AppColors.PrimaryGreen,
                modifier = Modifier.size(26.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = AppColors.TextPrimary
        )
    }
}
