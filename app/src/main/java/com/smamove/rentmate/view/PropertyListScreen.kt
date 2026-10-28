package com.smamove.rentmate.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Add
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
import com.smamove.rentmate.model.Property
import com.smamove.rentmate.model.UserRole
import com.smamove.rentmate.utils.AppColors

@Composable
fun PropertyListScreen(
    properties: List<Property>,
    onBackClick: () -> Unit,
    onNavigate: (String) -> Unit
) {
    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            AppBottomNavigation(
                currentRoute = "properties",
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
            AppTopHeader(onBackClick = onBackClick)

            Spacer(modifier = Modifier.height(8.dp))

            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = "Properties",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Your rental portfolio · Pune",
                    fontSize = 14.sp,
                    color = AppColors.TextSecondary
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Stats row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    PropertySummaryChip(label = "Total", count = "12", modifier = Modifier.weight(1f))
                    PropertySummaryChip(label = "Occupied", count = "10", isHighlighted = true, modifier = Modifier.weight(1f))
                    PropertySummaryChip(label = "Vacant", count = "2", modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Section header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "All properties",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.TextPrimary
                    )
                    Text(
                        text = "Showing 4 of 12",
                        fontSize = 12.sp,
                        color = AppColors.TextMuted
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Property list cards
                properties.forEach { property ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                            .clickable { /* property detail */ },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, AppColors.BorderLight)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
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
                                Spacer(modifier = Modifier.width(14.dp))
                                Column {
                                    Text(
                                        text = property.name,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = AppColors.TextPrimary
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = property.address,
                                        fontSize = 12.sp,
                                        color = AppColors.TextSecondary
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        StatusBadge(status = property.status)
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Text(
                                            text = property.rentAmount,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = AppColors.TextPrimary
                                        )
                                    }
                                }
                            }

                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = null,
                                tint = AppColors.TextMuted,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                AppButton(
                    text = "Add Property",
                    icon = Icons.Outlined.Add,
                    onClick = { /* Add Property */ }
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun PropertySummaryChip(
    label: String,
    count: String,
    modifier: Modifier = Modifier,
    isHighlighted: Boolean = false
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = if (isHighlighted) Color(0xFFEFFDF5) else AppColors.InputBackground
    ) {
        Column(
            modifier = Modifier.padding(vertical = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                fontSize = 12.sp,
                color = AppColors.TextSecondary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = count,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.TextPrimary
            )
        }
    }
}
