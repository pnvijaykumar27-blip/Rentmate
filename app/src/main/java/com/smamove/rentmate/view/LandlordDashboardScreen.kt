package com.smamove.rentmate.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
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
import com.smamove.rentmate.components.AppTopHeader
import com.smamove.rentmate.components.StatusBadge
import com.smamove.rentmate.model.Property
import com.smamove.rentmate.model.User
import com.smamove.rentmate.model.UserRole
import com.smamove.rentmate.utils.AppColors

@Composable
fun LandlordDashboardScreen(
    user: User,
    properties: List<Property>,
    onNavigate: (String) -> Unit
) {
    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            AppBottomNavigation(
                currentRoute = "dashboard",
                userRole = UserRole.LANDLORD,
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
                    text = "Welcome back,\n${user.name.ifBlank { "[Landlord Name]" }}",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary,
                    lineHeight = 34.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Your portfolio at a glance · 6 October 2026",
                    fontSize = 13.sp,
                    color = AppColors.TextSecondary
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Stats Grid (2x2)
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StatBox(
                            icon = Icons.Outlined.Apartment,
                            label = "Total Properties:",
                            valText = "12",
                            modifier = Modifier.weight(1f)
                        )
                        StatBox(
                            icon = Icons.Outlined.Circle,
                            label = "Occupied:",
                            valText = "10",
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StatBox(
                            icon = Icons.Outlined.Home,
                            label = "Vacant:",
                            valText = "2",
                            modifier = Modifier.weight(1f)
                        )
                        StatBox(
                            icon = Icons.Outlined.AccountBalanceWallet,
                            label = "Pending Rent:",
                            valText = "₹30,000",
                            modifier = Modifier.weight(1.1f),
                            isHighlight = true
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Quick Actions
                Text(
                    text = "Quick actions",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    LandlordQuickAction(
                        icon = Icons.Outlined.AddHome,
                        label = "Add Property",
                        onClick = { onNavigate("properties") }
                    )
                    LandlordQuickAction(
                        icon = Icons.Outlined.PersonAdd,
                        label = "Add Tenant",
                        onClick = { onNavigate("tenants") }
                    )
                    LandlordQuickAction(
                        icon = Icons.Outlined.AccountBalanceWallet,
                        label = "Collect Rent",
                        onClick = { onNavigate("payments") }
                    )
                    LandlordQuickAction(
                        icon = Icons.Outlined.Build,
                        label = "Maintenance",
                        onClick = { onNavigate("maintenance") }
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                // Properties Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Properties",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.TextPrimary
                    )
                    Text(
                        text = "View all 12",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AppColors.PrimaryGreen,
                        modifier = Modifier.clickable { onNavigate("properties") }
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    properties.take(2).forEach { prop ->
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { onNavigate("properties") },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            border = BorderStroke(1.dp, AppColors.BorderLight)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(36.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(Color(0xFFEFFDF5)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Home,
                                            contentDescription = null,
                                            tint = AppColors.PrimaryGreen,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                        contentDescription = null,
                                        tint = AppColors.TextMuted,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                Text(
                                    text = prop.name,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AppColors.TextPrimary
                                )

                                Spacer(modifier = Modifier.height(2.dp))

                                Text(
                                    text = prop.address,
                                    fontSize = 12.sp,
                                    color = AppColors.TextSecondary
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    StatusBadge(status = prop.status)
                                    Text(
                                        text = prop.rentAmount,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = AppColors.TextPrimary
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Showing 2 of 12 properties",
                    fontSize = 12.sp,
                    color = AppColors.TextMuted
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Rent Collection Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Rent Collection",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.TextPrimary
                    )
                    Text(
                        text = "October",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AppColors.PrimaryGreen
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = androidx.compose.foundation.BorderStroke(1.dp, AppColors.BorderLight)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text("Paid", fontSize = 12.sp, color = AppColors.TextSecondary)
                                Text("₹1,20,000", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = AppColors.PrimaryGreen)
                                Text("8 tenants", fontSize = 11.sp, color = AppColors.TextMuted)
                            }

                            Column {
                                Text("Pending", fontSize = 12.sp, color = AppColors.TextSecondary)
                                Text("₹30,000", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = AppColors.TextPrimary)
                                Text("2 tenants", fontSize = 11.sp, color = AppColors.TextMuted)
                            }

                            Column {
                                Text("Overdue", fontSize = 12.sp, color = AppColors.TextSecondary)
                                Text("₹30,000", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFFB45309))
                                Text("Due 5 October", fontSize = 11.sp, color = AppColors.TextMuted)
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Green progress bar
                        LinearProgressIndicator(
                            progress = { 0.8f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = AppColors.PrimaryGreen,
                            trackColor = Color(0xFFE5E7EB)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Overdue is included in Pending Rent, not additional.",
                            fontSize = 12.sp,
                            color = AppColors.TextMuted
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                // Maintenance Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Maintenance",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.TextPrimary
                    )
                    Text(
                        text = "View requests",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AppColors.PrimaryGreen,
                        modifier = Modifier.clickable { onNavigate("maintenance") }
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    MaintStatCard(icon = Icons.Outlined.Assignment, count = "2", label = "New Requests", modifier = Modifier.weight(1f))
                    MaintStatCard(icon = Icons.Outlined.Build, count = "1", label = "In Progress", modifier = Modifier.weight(1f))
                    MaintStatCard(icon = Icons.Outlined.CheckCircle, count = "5", label = "Completed", modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun StatBox(
    icon: ImageVector,
    label: String,
    valText: String,
    modifier: Modifier = Modifier,
    isHighlight: Boolean = false
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = if (isHighlight) Color(0xFFEFFDF5) else Color.White,
        border = androidx.compose.foundation.BorderStroke(1.dp, if (isHighlight) AppColors.LightGreenBorder else AppColors.BorderLight)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = AppColors.PrimaryGreen,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = label,
                    fontSize = 12.sp,
                    color = AppColors.TextSecondary
                )
            }
            Text(
                text = valText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = if (isHighlight) AppColors.PrimaryGreen else AppColors.TextPrimary
            )
        }
    }
}

@Composable
private fun LandlordQuickAction(
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
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = AppColors.TextPrimary
        )
    }
}

@Composable
private fun MaintStatCard(
    icon: ImageVector,
    count: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        border = androidx.compose.foundation.BorderStroke(1.dp, AppColors.BorderLight)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = AppColors.PrimaryGreen,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = count,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                fontSize = 11.sp,
                color = AppColors.TextSecondary
            )
        }
    }
}
