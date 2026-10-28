package com.smamove.rentmate.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.Home
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
import com.smamove.rentmate.components.AppButton
import com.smamove.rentmate.components.AppTopHeader
import com.smamove.rentmate.components.StatusBadge
import com.smamove.rentmate.model.UserRole
import com.smamove.rentmate.utils.AppColors

@Composable
fun ReceiptScreen(
    userRole: UserRole,
    onBackClick: () -> Unit,
    onNavigate: (String) -> Unit
) {
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
                    text = "Rent Receipt",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "October rent · 2026",
                    fontSize = 14.sp,
                    color = AppColors.TextSecondary
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Receipt Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, AppColors.BorderLight)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(Color(0xFFEFFDF5)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Home,
                                contentDescription = null,
                                tint = AppColors.PrimaryGreen,
                                modifier = Modifier.size(26.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Amount paid",
                            fontSize = 13.sp,
                            color = AppColors.TextSecondary
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = "₹15,000",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = AppColors.TextPrimary
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        StatusBadge(status = "Paid")

                        Spacer(modifier = Modifier.height(18.dp))
                        HorizontalDivider(color = AppColors.BorderLight)
                        Spacer(modifier = Modifier.height(18.dp))

                        ReceiptItemRow(label = "Property", value = "Green Residency")
                        Spacer(modifier = Modifier.height(12.dp))
                        ReceiptItemRow(label = "Unit", value = "Flat 204, Baner Road, Pune")
                        Spacer(modifier = Modifier.height(12.dp))
                        ReceiptItemRow(label = "Tenant", value = "Aarav Shah")
                        Spacer(modifier = Modifier.height(12.dp))
                        ReceiptItemRow(label = "Rent period", value = "October 2026")
                        Spacer(modifier = Modifier.height(12.dp))
                        ReceiptItemRow(label = "Payment method", value = "UPI")
                        Spacer(modifier = Modifier.height(12.dp))
                        ReceiptItemRow(label = "Transaction ID", value = "RM0510261500")
                        Spacer(modifier = Modifier.height(12.dp))
                        ReceiptItemRow(label = "Payment date", value = "5 October 2026")

                        Spacer(modifier = Modifier.height(18.dp))
                        HorizontalDivider(color = AppColors.BorderLight)
                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "Payment received by Rajesh Mehta.",
                            fontSize = 13.sp,
                            color = AppColors.TextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                AppButton(
                    text = "Download Receipt",
                    icon = Icons.Outlined.FileDownload,
                    onClick = { /* Download */ }
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Keep this receipt for your records.",
                    fontSize = 12.sp,
                    color = AppColors.TextMuted,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun ReceiptItemRow(label: String, value: String) {
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
