package com.smamove.rentmate.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smamove.rentmate.components.AppBottomNavigation
import com.smamove.rentmate.components.AppTopHeader
import com.smamove.rentmate.model.UserRole
import com.smamove.rentmate.utils.AppColors

@Composable
fun SettingsScreen(
    userRole: UserRole,
    onBackClick: () -> Unit,
    onNavigate: (String) -> Unit
) {
    var darkMode by remember { mutableStateOf(false) }
    var rentReminders by remember { mutableStateOf(true) }
    var maintenanceUpdates by remember { mutableStateOf(true) }
    var messageNotifications by remember { mutableStateOf(true) }
    var biometricLogin by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            AppBottomNavigation(
                currentRoute = "profile",
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
                    text = "Settings",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Make RentMate work for you.",
                    fontSize = 14.sp,
                    color = AppColors.TextSecondary
                )

                Spacer(modifier = Modifier.height(24.dp))

                // SECTION 1: ACCOUNT
                SettingsSectionHeader(title = "ACCOUNT")
                SettingsClickableRow(label = "Account Details", onClick = {})
                HorizontalDivider(color = AppColors.BorderLight)
                SettingsClickableRow(label = "Change Password", onClick = {})

                Spacer(modifier = Modifier.height(24.dp))

                // SECTION 2: PREFERENCES
                SettingsSectionHeader(title = "PREFERENCES")
                SettingsClickableRow(label = "Language", valueText = "English", onClick = {})
                HorizontalDivider(color = AppColors.BorderLight)
                SettingsToggleRow(label = "Dark Mode", checked = darkMode, onCheckedChange = { darkMode = it })

                Spacer(modifier = Modifier.height(24.dp))

                // SECTION 3: NOTIFICATIONS
                SettingsSectionHeader(title = "NOTIFICATIONS")
                SettingsToggleRow(label = "Rent Reminders", checked = rentReminders, onCheckedChange = { rentReminders = it })
                HorizontalDivider(color = AppColors.BorderLight)
                SettingsToggleRow(label = "Maintenance Updates", checked = maintenanceUpdates, onCheckedChange = { maintenanceUpdates = it })
                HorizontalDivider(color = AppColors.BorderLight)
                SettingsToggleRow(label = "Message Notifications", checked = messageNotifications, onCheckedChange = { messageNotifications = it })

                Spacer(modifier = Modifier.height(24.dp))

                // SECTION 4: PRIVACY & SECURITY
                SettingsSectionHeader(title = "PRIVACY & SECURITY")
                SettingsToggleRow(label = "Biometric Login", checked = biometricLogin, onCheckedChange = { biometricLogin = it })
                HorizontalDivider(color = AppColors.BorderLight)
                SettingsClickableRow(label = "Privacy Policy", onClick = {})
                HorizontalDivider(color = AppColors.BorderLight)
                SettingsClickableRow(label = "Manage Devices", valueText = "1 device", onClick = {})

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "RentMate · Version 1.0.0",
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
private fun SettingsSectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = AppColors.PrimaryGreen,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
private fun SettingsClickableRow(
    label: String,
    valueText: String = "",
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = AppColors.TextPrimary
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            if (valueText.isNotBlank()) {
                Text(
                    text = valueText,
                    fontSize = 14.sp,
                    color = AppColors.TextMuted
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = AppColors.TextMuted,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
private fun SettingsToggleRow(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = AppColors.TextPrimary
        )

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = AppColors.PrimaryGreen,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = AppColors.BorderLight
            )
        )
    }
}
