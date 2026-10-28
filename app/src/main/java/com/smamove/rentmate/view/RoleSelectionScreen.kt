package com.smamove.rentmate.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.smamove.rentmate.model.UserRole
import com.smamove.rentmate.utils.AppColors

@Composable
fun RoleSelectionScreen(
    onRoleSelected: (UserRole) -> Unit,
    onBackClick: () -> Unit
) {
    var selectedRole by remember { mutableStateOf(UserRole.TENANT) }

    Scaffold(
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, AppColors.BorderLight, RoundedCornerShape(12.dp))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = AppColors.TextPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Personalize your experience",
                    fontSize = 14.sp,
                    color = AppColors.TextSecondary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "How will you use\nRentMate?",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.TextPrimary,
                lineHeight = 34.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Choose your role to make RentMate yours.",
                fontSize = 14.sp,
                color = AppColors.TextSecondary
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Tenant Card
            RoleCard(
                title = "Tenant",
                features = listOf(
                    "Find/manage rental property",
                    "Pay rent",
                    "Raise maintenance requests",
                    "Communicate with landlord"
                ),
                isSelected = selectedRole == UserRole.TENANT,
                icon = Icons.Outlined.Person,
                onSelect = { selectedRole = UserRole.TENANT },
                onContinue = { onRoleSelected(UserRole.TENANT) },
                buttonText = "Continue as Tenant"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Landlord Card
            RoleCard(
                title = "Landlord",
                features = listOf(
                    "Manage properties",
                    "Manage tenants",
                    "Track rent",
                    "Manage maintenance"
                ),
                isSelected = selectedRole == UserRole.LANDLORD,
                icon = Icons.Outlined.Apartment,
                onSelect = { selectedRole = UserRole.LANDLORD },
                onContinue = { onRoleSelected(UserRole.LANDLORD) },
                buttonText = "Continue as Landlord"
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.HelpOutline,
                    contentDescription = null,
                    tint = AppColors.TextMuted,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "You can change your role later in Settings.",
                    fontSize = 13.sp,
                    color = AppColors.TextMuted
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun RoleCard(
    title: String,
    features: List<String>,
    isSelected: Boolean,
    icon: ImageVector,
    onSelect: () -> Unit,
    onContinue: () -> Unit,
    buttonText: String
) {
    val borderColor = if (isSelected) AppColors.PrimaryGreen else AppColors.BorderLight
    val bgColor = if (isSelected) Color(0xFFEFFDF5) else Color.White

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(bgColor)
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onSelect() }
            .padding(20.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(if (isSelected) Color.White else Color(0xFFEFFDF5)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = AppColors.PrimaryGreen,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.TextPrimary
                    )
                }

                // Selection check indicator
                Box(
                    modifier = Modifier
                        .size(24.dp)
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
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = null,
                            tint = AppColors.PrimaryGreen,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            features.forEach { feature ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = null,
                        tint = AppColors.PrimaryGreen,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = feature,
                        fontSize = 14.sp,
                        color = AppColors.TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            AppButton(
                text = buttonText,
                onClick = onContinue,
                backgroundColor = AppColors.PrimaryGreen
            )
        }
    }
}
