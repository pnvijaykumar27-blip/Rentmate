package com.smamove.rentmate.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
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
import com.smamove.rentmate.components.AppButton
import com.smamove.rentmate.components.AppTopHeader
import com.smamove.rentmate.components.StatusBadge
import com.smamove.rentmate.model.RentalPayment
import com.smamove.rentmate.model.UserRole
import com.smamove.rentmate.utils.AppColors

@Composable
fun PaymentScreen(
    payments: List<RentalPayment>,
    userRole: UserRole,
    onBackClick: () -> Unit,
    onNavigate: (String) -> Unit
) {
    var selectedTab by remember { mutableStateOf("Paid") }

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            AppBottomNavigation(
                currentRoute = "payments",
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
                    text = "Rent Collection",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "October 2026 · As of 6 October",
                    fontSize = 14.sp,
                    color = AppColors.TextSecondary
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Summary Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEFFDF5))
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = "Pending Rent · 2 tenants",
                                    fontSize = 12.sp,
                                    color = AppColors.TextSecondary
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "₹30,000",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AppColors.TextPrimary
                                )
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "Paid · 8 tenants",
                                    fontSize = 12.sp,
                                    color = AppColors.TextSecondary
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "₹1,20,000",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AppColors.PrimaryGreen
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

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
                            text = "Overdue ₹30,000 · Due 5 October 2026",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFB45309)
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = "Overdue is included in Pending Rent, not additional.",
                            fontSize = 12.sp,
                            color = AppColors.TextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Filter pills (Paid (8), Pending (2), Overdue (2))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FilterPill(
                        label = "Paid (8)",
                        isSelected = selectedTab == "Paid",
                        onClick = { selectedTab = "Paid" },
                        modifier = Modifier.weight(1f)
                    )
                    FilterPill(
                        label = "Pending (2)",
                        isSelected = selectedTab == "Pending",
                        onClick = { selectedTab = "Pending" },
                        modifier = Modifier.weight(1f)
                    )
                    FilterPill(
                        label = "Overdue (2)",
                        isSelected = selectedTab == "Overdue",
                        onClick = { selectedTab = "Overdue" },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Payments list
                payments.forEach { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                            .clickable {
                                if (item.status == "Paid") {
                                    onNavigate("receipt")
                                } else {
                                    onNavigate("pay_rent")
                                }
                            },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, AppColors.BorderLight)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Top
                            ) {
                                Column {
                                    Text(
                                        text = item.tenantName,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = AppColors.TextPrimary
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = item.propertyDetails,
                                        fontSize = 12.sp,
                                        color = AppColors.TextSecondary
                                    )
                                }

                                Text(
                                    text = item.amount,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AppColors.TextPrimary
                                )
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = item.dateOrDue,
                                    fontSize = 12.sp,
                                    color = AppColors.TextMuted
                                )
                                StatusBadge(status = item.status)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                AppButton(
                    text = "Collect Rent",
                    icon = Icons.Outlined.AccountBalanceWallet,
                    onClick = { onNavigate("pay_rent") }
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun FilterPill(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        modifier = modifier.height(44.dp),
        shape = RoundedCornerShape(14.dp),
        color = if (isSelected) Color(0xFFEFFDF5) else Color.White,
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isSelected) AppColors.PrimaryGreen else AppColors.BorderLight
        )
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = label,
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) AppColors.PrimaryGreen else AppColors.TextSecondary
            )
        }
    }
}
