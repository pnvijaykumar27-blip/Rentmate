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
import androidx.compose.material.icons.outlined.FileDownload
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
import com.smamove.rentmate.components.AppButton
import com.smamove.rentmate.components.AppOutlinedButton
import com.smamove.rentmate.components.AppTopHeader
import com.smamove.rentmate.components.StatusBadge
import com.smamove.rentmate.utils.AppColors

@Composable
fun PaymentSuccessfulScreen(
    onDownloadReceiptClick: () -> Unit,
    onBackToDashboardClick: () -> Unit,
    onBackClick: () -> Unit
) {
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

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Large Check Circle
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
                    text = "Payment Successful!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Your October rent is paid.\nYou're all set for the month.",
                    fontSize = 14.sp,
                    color = AppColors.TextSecondary,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Card details
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
                        Text(
                            text = "Amount paid",
                            fontSize = 13.sp,
                            color = AppColors.TextSecondary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
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

                        DetailRow(label = "Property", value = "Green Residency")
                        Spacer(modifier = Modifier.height(12.dp))
                        DetailRow(label = "Rent period", value = "October")
                        Spacer(modifier = Modifier.height(12.dp))
                        DetailRow(label = "Due date", value = "5 October")
                        Spacer(modifier = Modifier.height(12.dp))
                        DetailRow(label = "Payment method", value = "UPI")
                        Spacer(modifier = Modifier.height(12.dp))
                        DetailRow(label = "Transaction ID", value = "RM0510261500")
                        Spacer(modifier = Modifier.height(12.dp))
                        DetailRow(label = "Payment date", value = "5 October 2026")
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                AppOutlinedButton(
                    text = "Download Receipt",
                    icon = Icons.Outlined.FileDownload,
                    onClick = onDownloadReceiptClick
                )

                Spacer(modifier = Modifier.height(12.dp))

                AppButton(
                    text = "Back to Dashboard",
                    onClick = onBackToDashboardClick
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
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
