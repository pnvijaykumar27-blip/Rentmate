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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smamove.rentmate.components.AppButton
import com.smamove.rentmate.components.AppTopHeader
import com.smamove.rentmate.utils.AppColors

@Composable
fun PayRentScreen(
    onPaySuccess: (String) -> Unit,
    onBackClick: () -> Unit
) {
    var selectedMethod by remember { mutableStateOf("UPI") }

    Scaffold(
        containerColor = Color.White
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
                    text = "Pay Rent",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "One simple payment. Peace of mind.",
                    fontSize = 14.sp,
                    color = AppColors.TextSecondary
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Summary Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, AppColors.BorderLight)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFFEFFDF5)),
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
                                    text = "Green Residency",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AppColors.TextPrimary
                                )
                                Text(
                                    text = "Flat 204, Baner Road, Pune",
                                    fontSize = 13.sp,
                                    color = AppColors.TextSecondary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider(color = AppColors.BorderLight)
                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Monthly rent", fontSize = 14.sp, color = AppColors.TextSecondary)
                            Text("₹15,000", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = AppColors.TextPrimary)
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Due date", fontSize = 14.sp, color = AppColors.TextSecondary)
                            Text("5 October", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = AppColors.TextPrimary)
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Late fee", fontSize = 14.sp, color = AppColors.TextSecondary)
                            Text("₹0", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = AppColors.TextPrimary)
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider(color = AppColors.BorderLight)
                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Total amount", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = AppColors.TextPrimary)
                            Text("₹15,000", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = AppColors.PrimaryGreen)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "Choose payment method",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Methods list
                PaymentMethodCard(
                    title = "UPI",
                    subtitle = "Google Pay, PhonePe, Paytm & more",
                    icon = Icons.Outlined.Smartphone,
                    isSelected = selectedMethod == "UPI",
                    onClick = { selectedMethod = "UPI" }
                )

                Spacer(modifier = Modifier.height(12.dp))

                PaymentMethodCard(
                    title = "Debit/Credit Card",
                    subtitle = "Visa, Mastercard or RuPay",
                    icon = Icons.Outlined.CreditCard,
                    isSelected = selectedMethod == "Debit/Credit Card",
                    onClick = { selectedMethod = "Debit/Credit Card" }
                )

                Spacer(modifier = Modifier.height(12.dp))

                PaymentMethodCard(
                    title = "Net Banking",
                    subtitle = "Pay directly from your bank account",
                    icon = Icons.Outlined.AccountBalance,
                    isSelected = selectedMethod == "Net Banking",
                    onClick = { selectedMethod = "Net Banking" }
                )

                Spacer(modifier = Modifier.height(28.dp))

                AppButton(
                    text = "Pay ₹15,000",
                    onClick = { onPaySuccess(selectedMethod) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.VerifiedUser,
                        contentDescription = null,
                        tint = AppColors.TextMuted,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Secure and encrypted payments",
                        fontSize = 12.sp,
                        color = AppColors.TextMuted
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun PaymentMethodCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) Color(0xFFEFFDF5) else Color.White,
        border = androidx.compose.foundation.BorderStroke(
            if (isSelected) 2.dp else 1.dp,
            if (isSelected) AppColors.PrimaryGreen else AppColors.BorderLight
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) Color.White else AppColors.InputBackground),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = AppColors.PrimaryGreen,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.TextPrimary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = subtitle,
                        fontSize = 12.sp,
                        color = AppColors.TextSecondary
                    )
                }
            }

            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) AppColors.PaidBackground else Color.Transparent)
                    .border(
                        1.dp,
                        if (isSelected) AppColors.PrimaryGreen else AppColors.BorderLight,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(AppColors.PrimaryGreen)
                    )
                }
            }
        }
    }
}
