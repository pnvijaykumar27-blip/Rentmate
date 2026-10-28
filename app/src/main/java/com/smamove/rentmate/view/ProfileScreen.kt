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
import com.smamove.rentmate.model.User
import com.smamove.rentmate.model.UserRole
import com.smamove.rentmate.utils.AppColors

@Composable
fun ProfileScreen(
    user: User,
    onSettingsClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onNavigate: (String) -> Unit
) {
    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            AppBottomNavigation(
                currentRoute = "profile",
                userRole = user.role,
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
                    text = "My Profile",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Your rental and account details.",
                    fontSize = 14.sp,
                    color = AppColors.TextSecondary
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Avatar Header
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(68.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFEFFDF5)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (user.name.contains(" ")) {
                                "${user.name.first()}${user.name.split(" ").last().first()}"
                            } else "AS",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = AppColors.PrimaryGreen
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = user.name.ifBlank { "Aarav Shah" },
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = AppColors.TextPrimary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        StatusBadge(status = if (user.role == UserRole.LANDLORD) "Landlord" else "Tenant")
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Contact Details Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, AppColors.BorderLight)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Email", fontSize = 14.sp, color = AppColors.TextSecondary)
                            Text(user.email.ifBlank { "aarav.shah@email.com" }, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = AppColors.TextPrimary)
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Phone", fontSize = 14.sp, color = AppColors.TextSecondary)
                            Text(user.phone.ifBlank { "+91 98765 43210" }, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = AppColors.TextPrimary)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Rental Property Details Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEFFDF5))
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = user.propertyName.ifBlank { "Green Residency" },
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = AppColors.TextPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = user.unitDetails.ifBlank { "Flat 204, Baner Road, Pune" },
                            fontSize = 13.sp,
                            color = AppColors.TextSecondary
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Monthly rent", fontSize = 13.sp, color = AppColors.TextSecondary)
                            Text(user.monthlyRent.ifBlank { "₹15,000" }, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = AppColors.TextPrimary)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Lease ends", fontSize = 13.sp, color = AppColors.TextSecondary)
                            Text(user.leaseEnds.ifBlank { "30 September 2027" }, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = AppColors.TextPrimary)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Landlord", fontSize = 13.sp, color = AppColors.TextSecondary)
                            Text(user.landlordName.ifBlank { "Rajesh Mehta" }, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = AppColors.TextPrimary)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Menu items
                ProfileMenuItem(label = "Edit Profile", onClick = { /* Edit */ })
                HorizontalDivider(color = AppColors.BorderLight)

                ProfileMenuItem(label = "Rental Agreement", onClick = { /* Agreement */ })
                HorizontalDivider(color = AppColors.BorderLight)

                ProfileMenuItem(label = "Settings", onClick = onSettingsClick)
                HorizontalDivider(color = AppColors.BorderLight)

                ProfileMenuItem(label = "Help & Support", onClick = { /* Help */ })

                Spacer(modifier = Modifier.height(28.dp))

                AppOutlinedButton(
                    text = "Log Out",
                    onClick = onLogoutClick
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun ProfileMenuItem(
    label: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = AppColors.TextPrimary
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = AppColors.TextMuted,
            modifier = Modifier.size(22.dp)
        )
    }
}
